package online.mokkoji.common.auth.oauth2.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.user.dto.response.MainPageDto;
import online.mokkoji.user.dto.response.SignupPageDto;
import online.mokkoji.common.auth.jwt.config.JwtConfig;
import online.mokkoji.common.auth.jwt.util.JwtUtil;
import online.mokkoji.user.domain.Provider;
import online.mokkoji.user.domain.User;
import online.mokkoji.user.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtService;
    private final JwtConfig jwtConfig;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("소셜 로그인 성공!!");
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String email = oAuth2User.getAttribute("email");
        Provider provider = oAuth2User.getAttribute("provider");
        boolean isExist = oAuth2User.getAttribute("exist");


        if (!isExist) {
            log.info("회원가입 페이지로 이동합니다.");
            String accessToken = jwtService.createAccessToken(provider.getKey(), email);
            response.setHeader(jwtConfig.getAccessHeader(), accessToken);

            SignupPageDto signupPageDto = new SignupPageDto((String) attributes.get("email"),
                    (String) attributes.get("name"), (String) attributes.get("image"));
            response.getWriter().write(objectMapper.writeValueAsString(signupPageDto));

            response.sendRedirect("/signup");
        } else {
            log.info("메인 페이지로 이동합니다");
            String accessToken = jwtService.createAccessToken(provider.getKey(), email);
            String refreshToken = jwtService.createRefreshToken();

            User loginUser = userRepository.findByProviderAndEmail(provider, email).get();
            loginUser.updateRefreshToken(refreshToken);
            MainPageDto mainPageDto = new MainPageDto(loginUser.getImage(), loginUser.getName());

            jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);
            response.getWriter().write(objectMapper.writeValueAsString(mainPageDto));
            response.sendRedirect("/");
        }
    }
}
