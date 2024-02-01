package online.mokkoji.event.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PhotoResDto {
    private Long userId;
    private Long resultId;
    private String photoUrl;
}
