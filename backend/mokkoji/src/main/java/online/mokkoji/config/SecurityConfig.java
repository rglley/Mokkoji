package online.mokkoji.config;

import lombok.RequiredArgsConstructor;
import online.mokkoji.common.auth.CustomOAuth2UserService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                //authorizeRequests : URL별 권한 관리 설정의 시작점
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                //permitAll 회의 참여하기 추가 필요
                                .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                                .requestMatchers("/users").hasRole("USER")
                                //.requestMatchers("/admin").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )

                //logout 성공 시 설정 주소로 이동
                .logout(logout -> logout.logoutSuccessUrl("/"))

                //OAuth2 로그인 성공 이후 사용자 정보를 가져올 때 customOAuth2UserService를 사용
                .oauth2Login(oauth2Login ->
                        oauth2Login.userInfoEndpoint(userInfoEndpointConfig ->
                                userInfoEndpointConfig.userService(customOAuth2UserService)
                        )
                );

        return http.build();
    }
}
