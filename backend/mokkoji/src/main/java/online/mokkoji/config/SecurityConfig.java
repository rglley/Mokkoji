package online.mokkoji.config;

import lombok.RequiredArgsConstructor;
import online.mokkoji.common.auth.filter.JwtAuthenticationProcessingFilter;
import online.mokkoji.common.auth.handler.OAuth2LoginFailureHandler;
import online.mokkoji.common.auth.handler.OAuth2LoginSuccessHandler;
import online.mokkoji.common.auth.service.CustomOAuth2UserService;
import online.mokkoji.common.auth.service.JwtService;
import online.mokkoji.db.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final OAuth2LoginSuccessHandler oauth2Loginsuccesshandler;
    private final OAuth2LoginFailureHandler oauth2Loginfailurehandler;
    private final CustomOAuth2UserService customOAuth2UserService;

    //http가 아니고 webConfiguration에서 기본 로그인을 없애줘야 하는듯?
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(formLoginConfigurer -> formLoginConfigurer.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .csrf(csrf -> csrf.disable())

                //h2 테스트
                .headers(headers -> headers.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()))

                //URL 별 권한 관리
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/favicon.ico",
                                        "/h2-console/**").permitAll()

                                //회의 참여 등 비회원 가능 url 추가 필요
                                .requestMatchers("/signup", "/login/oauth2/code/**").permitAll()
                                .anyRequest().authenticated()
                )

                //소셜 로그인
                .oauth2Login(oauth2Login ->
                        oauth2Login.successHandler(oauth2Loginsuccesshandler)
                                .failureHandler(oauth2Loginfailurehandler)
                                .userInfoEndpoint(userInfoEndpointConfig ->
                                        userInfoEndpointConfig.userService(customOAuth2UserService)
                                )
                );

        http.addFilterBefore(jwtAuthenticationProcessingFilter(), LogoutFilter.class);

        return http.build();
    }

    @Bean
    public JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter() {
        JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter =
                new JwtAuthenticationProcessingFilter(jwtService, userRepository);
        return jwtAuthenticationProcessingFilter;
    }
}
