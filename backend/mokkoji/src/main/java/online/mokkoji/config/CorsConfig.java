package online.mokkoji.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:5173",
                        "http://mokkoji.online:5173",
                        "https://mokkoji.online:5173",
                        "http://mokkoji.online",
                        "https://mokkoji.online",
                        "http://i10a401.p.ssafy.io:5173",
                        "https://i10a401.p.ssafy.io:5173",
                        "http://i10a401.p.ssafy.io",
                        "https://i10a401.p.ssafy.io"
                )
                .allowedMethods("GET", "PUT", "POST", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true)
                .exposedHeaders("Authorization", "Authorization-Refresh")
                .maxAge(3600);
    }
}