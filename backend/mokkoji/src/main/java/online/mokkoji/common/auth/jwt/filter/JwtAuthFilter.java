package online.mokkoji.common.auth.jwt.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.common.auth.jwt.dto.AuthUserDto;
import online.mokkoji.common.auth.jwt.util.JwtUtil;
import online.mokkoji.user.domain.Provider;
import online.mokkoji.user.domain.User;
import online.mokkoji.user.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private static final String MAIN_URL = "/";
    private static final String LOGIN_URL = "/oauth2";
    private static final String TEST_URL = "/api/v1";

//  private static final String JOIN_URL = "sessions/${sessionId}";
//  private static final String JOIN_URL = "sessions/${sessionId}/connections";

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //인가가 불필요한 요청은 jwtAuthFilter 통과
        if (request.getRequestURI().equals(MAIN_URL) ||
                request.getRequestURI().startsWith(LOGIN_URL)|| request.getRequestURI().startsWith(TEST_URL)){
            filterChain.doFilter(request, response);
            return;
        }

        String refreshToken = jwtUtil.extractRefreshToken(request)
                .filter(jwtUtil::isTokenValid)
                .orElse(null);

        //RefreshToken 유효성 검사, AccessToken 재발급
        if (refreshToken != null) {
            checkAndUpdateToken(response, refreshToken);
            filterChain.doFilter(request, response);
            return;
        }

        //AccessToken 유효성 검사
        if (refreshToken == null) {
            checkValidity(request);
            filterChain.doFilter(request, response);
        }
    }

    /**
     * RefreshToken 유효성 검사, AccessToken 재발급
     * @param response 헤더에 토큰을 담을 response
     * @param refreshToken Jwt refreshToken
     */
    public void checkAndUpdateToken(HttpServletResponse response, String refreshToken) {

        Optional<User> findUser = userRepository.findByRefreshToken(refreshToken);

        if (findUser.isEmpty()) {
            throw new JwtException("RefreshToken is not valid");
        }

        User loginUser = findUser.get();
        String newRefreshToken = jwtUtil.createRefreshToken();
        loginUser.updateRefreshToken(newRefreshToken);
        userRepository.saveAndFlush(loginUser);

        String newAccessToken = jwtUtil.createAccessToken(loginUser.getProvider().getKey(), loginUser.getEmail());

        jwtUtil.sendAccessAndRefreshToken(response, newAccessToken, newRefreshToken);
    }

    /**
     * AccessToken 유효성 검사
     * @param req 헤더에 AccessToken이 담긴 request
     * @throws ServletException
     * @throws IOException
     */
    public void checkValidity(HttpServletRequest req) throws ServletException, IOException {

        Optional<String> extractAccessToken = jwtUtil.extractAccessToken(req);

        if (extractAccessToken.isEmpty()) {
            throw new JwtException("헤더에서 AccessToken을 찾을 수 없습니다.");
        }

        String accessToken = extractAccessToken.get();
        Optional<String> extractProvider = jwtUtil.extractProvider(accessToken);
        Optional<String> extractEmail = jwtUtil.extractEmail(accessToken);

        if (extractEmail.isEmpty() || extractProvider.isEmpty()) {
            throw new JwtException("AccessToken claim이 올바르지 않습니다");
        }

        //provider, email을 클레임으로 가진다.
        String provider = extractProvider.get();
        String email = extractEmail.get();

        Optional<User> findUser = userRepository.findByProviderAndEmail(Provider.valueOf(provider), email);
        if (findUser.isEmpty()) {
            throw new JwtException("존재하지 않는 회원의 토큰입니다");
        }

        User varifyUSer = findUser.get();
        AuthUserDto authUser = AuthUserDto.builder()
                .userNo(varifyUSer.getId())
                .provider(varifyUSer.getProvider().getKey())
                .email(varifyUSer.getEmail())
                .role(varifyUSer.getAuthority().getKey().substring(5))
                .build();

        saveAuthentication(authUser);
    }

    /**
     * 사용자 인증 정보 저장
     * @param user 소셜 로그인 유저 정보
     */
    public void saveAuthentication(AuthUserDto user) {
        UserDetails userDetailsUser = org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getProvider())
                .roles(user.getRole())
                .build();


        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDetailsUser, user.getProvider(),
                        authoritiesMapper.mapAuthorities(userDetailsUser.getAuthorities()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
