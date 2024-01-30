package online.mokkoji.common.auth.jwt.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
@NoArgsConstructor
@AllArgsConstructor
public class JwtConfig {

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.access.expiration}")
    private Long accessExpiration;

    @Value("${jwt.access.header}")
    private String accessHeader;

    @Value("${jwt.refresh.expiration}")
    private Long refreshExpiration;

    @Value("${jwt.refresh.header}")
    private String refreshHeader;
}
