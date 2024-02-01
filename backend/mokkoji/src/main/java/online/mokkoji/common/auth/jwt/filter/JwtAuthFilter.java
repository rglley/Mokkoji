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
//OncePerRequestFilter : 요청당 한 번만 필터 적용되게 하는 Spring Security 추상 클래스
public class JwtAuthFilter extends OncePerRequestFilter {
    //토큰 인증이 필요 없는 요청 : 회의참가(추가 필요)
    private static final String MAIN_URL = "/";
    private static final String JOIN_URL = "/signup";
    private static final String LOGIN_URL = "/users/login";

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    //Spring Security 사용자 인증 권한 매퍼
    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getRequestURI().equals(JOIN_URL) || request.getRequestURI().equals(MAIN_URL) ||
                request.getRequestURI().equals(LOGIN_URL)){
            log.info("JWT 인증 불필요한 요청 접수, JwtAuthFilter 종료");
            filterChain.doFilter(request, response); //JwtAuthFilter 다음 필터 호출
            return;
        }

        String refreshToken = jwtUtil.extractRefreshToken(request)
                .filter(jwtUtil::isTokenValid)
                .orElse(null);

        if (refreshToken != null) {
            log.info("AccessToken 재발급 요청, 검증 진행 X RefreshToken : {}", refreshToken);
            checkAndUpdateToken(response, refreshToken);
            filterChain.doFilter(request, response);
            return;
        }

        if (refreshToken == null) {
            log.info("AccessToken 검증");
            checkValidity(request, response, filterChain);
            filterChain.doFilter(request, response);
        }
    }

    public void checkAndUpdateToken(HttpServletResponse response, String refreshToken) {

        Optional<User> findUser = userRepository.findByRefreshToken(refreshToken);

        if (findUser.isEmpty()) {
            log.error("RefreshToken에 해당하는 회원 정보 존재 X, AccessToken 재발급 불가능");
            throw new JwtException("RefreshToken is not valid");
        }

        User loginUser = findUser.get();
        String newRefreshToken = jwtUtil.createRefreshToken();
        loginUser.updateRefreshToken(newRefreshToken);
        userRepository.saveAndFlush(loginUser);
        log.info("RefreshToken 갱신, {}", newRefreshToken);

        String newAccessToken = jwtUtil.createAccessToken(loginUser.getProvider().getKey(), loginUser.getEmail());
        log.info("AccessToken 재발급, {}", newAccessToken);

        jwtUtil.sendAccessAndRefreshToken(response, newAccessToken, newRefreshToken);
    }


    public void checkValidity(HttpServletRequest req, HttpServletResponse resp,
                              FilterChain filterChain) throws ServletException, IOException {

        log.info("AccessToken 유효성 검사 진행");
        Optional<String> extractAccessToken = jwtUtil.extractAccessToken(req);

        if (extractAccessToken.isEmpty()) {
            log.error("유효하지 않은 AccessToken!");
            throw new JwtException("헤더에서 AccessToken을 찾을 수 없습니다.");
        }

        String accessToken = extractAccessToken.get();
        Optional<String> extractProvider = jwtUtil.extractEmail(accessToken);
        Optional<String> extractEmail = jwtUtil.extractEmail(accessToken);

        if (extractEmail.isEmpty() || extractProvider.isEmpty()) {
            log.error("유효하지 않은 AccessToken!");
            throw new JwtException("AccessToken claim이 올바르지 않습니다");
        }

        String provider = extractProvider.get();
        String email = extractProvider.get();

        Optional<User> findUser = userRepository.findByProviderAndEmail(Provider.valueOf(provider), email);
        if (findUser.isEmpty()) {
            log.error("회원 정보를 찾을 수 없습니다!");
            throw new JwtException("존재하지 않는 회원의 토큰입니다");
        }

        log.info("인증 허가 저장");
        User varifyUSer = findUser.get();
        AuthUserDto authUser = AuthUserDto.builder()
                .userNo(varifyUSer.getId())
                .provider(varifyUSer.getProvider().getKey())
                .email(varifyUSer.getEmail())
                .role(varifyUSer.getAuthority().getKey())
                .build();

        saveAuthentication(authUser);
    }

    public void saveAuthentication(AuthUserDto user) {
        //UserDetails : Spring Security의 사용자 세부 정보를 나타내는 인터페이스
        UserDetails userDetailsUser = org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getProvider())
                .roles(user.getRole())
                .build();

        //Authentication : 사용자 인증 정보를 나타내는 인터페이스
        Authentication authentication =
                //UsernamePasswordAuthenticationToken : 사용자 이름, 비밀번호를 사용해 인증을 나타내는 가장 일반적인 형태
                new UsernamePasswordAuthenticationToken(userDetailsUser, user.getProvider(),
                        authoritiesMapper.mapAuthorities(userDetailsUser.getAuthorities()));

        //인증 정보가 Authentication에, Authentication이 SecurityContext에, SecutiryContext가 SecurityContextHolder에 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
