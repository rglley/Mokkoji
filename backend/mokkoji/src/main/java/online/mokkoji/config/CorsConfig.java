package online.mokkoji.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "PUT", "POST", "DELETE")
                .allowedHeaders("Authroization", "Authorization-Refresh")
                .allowCredentials(true)
                .exposedHeaders("Authorization", "Authorization-Refresh")
                .maxAge(3600);
    }
}
