package online.mokkoji.event.dto.response;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@RedisHash("photos")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhotoResDto {

    @Id
    private String photoId;

    @Indexed
    private Long userId;
    @Indexed
    private Long resultId;
    private String photoUrl;

}
