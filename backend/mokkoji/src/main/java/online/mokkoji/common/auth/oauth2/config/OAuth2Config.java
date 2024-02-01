package online.mokkoji.common.auth.oauth2.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
@NoArgsConstructor
@AllArgsConstructor
public class OAuth2Config {

    @Value("${spring.security.oauth2.client.provider.naver.token_uri}")
    private String naverTokenUrl;

    @Value("${spring.security.oauth2.client.provider.naver.user-info-uri}")
    private String naverProfileUrl;

    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String naverId;

    @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
    private String naverSecret;
}
