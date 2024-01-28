package online.mokkoji.common.auth.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.api.response.SignupPageDto;
import online.mokkoji.common.auth.jwt.JwtProperties;
import online.mokkoji.common.auth.jwt.JwtService;
import online.mokkoji.db.entity.User.Provider;
import online.mokkoji.db.entity.User.User;
import online.mokkoji.db.repository.User.UserRepository;
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

    private final JwtService jwtService;
    private final JwtProperties jwtProperties;
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
            response.setHeader(jwtProperties.getAccessHeader(), accessToken);

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

            jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);
            response.sendRedirect("/");
        }
    }
}
