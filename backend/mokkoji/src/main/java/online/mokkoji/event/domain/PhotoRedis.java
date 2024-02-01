package online.mokkoji.event.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@RedisHash("photos")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhotoRedis {

    @Id
    private String photoId;

    @Indexed
    private String userId;
    @Indexed
    private String resultId;
    private String photoUrl;

    public PhotoRedis(String userId, String resultId, String photoUrl) {
        this.userId = userId;
        this.resultId = resultId;
        this.photoUrl = photoUrl;
    }
}
