package online.mokkoji.common.auth.jwt;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
//@RedisHash(value = "jwtToken", timeToLive = 60 * 60 * 24 * 14)
public class JwtToken implements Serializable {

    @EmbeddedId
    private JwtId jwtId;

    @Indexed
    private String accessToken;

    private String refreshToken;

    public void updateAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
