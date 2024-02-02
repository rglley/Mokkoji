package online.mokkoji.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {
//    private final JwtAuthFilter jwtAuthFilter;
//    private final JwtExceptionFilter jwtExceptionFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(httpSecurityHttpBasicConfigurer -> httpSecurityHttpBasicConfigurer.disable())
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())

                //h2 테스트
                .headers(headers -> headers.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()))

                //jwt는 세션 사용 X, 화상회의에서 쓰지 않나?
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                //URL 별 권한 관리
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/favicon.ico",
                                        "/h2-console/**").permitAll()
                                //회의 참여 등 비회원 가능 url 추가 필요
                                .requestMatchers("/signup", "/oauth2/login/**").permitAll()
                                .anyRequest().permitAll()
                );

//                .addFilterAfter(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(jwtExceptionFilter, JwtAuthFilter.class);

        return http.build();
    }
}

