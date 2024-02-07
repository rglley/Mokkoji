package online.mokkoji.result.dto.response;

import lombok.*;
import online.mokkoji.result.domain.RollingPaper.BackgroundTemplate;
import online.mokkoji.result.domain.RollingPaper.PostitTemplate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RollingpaperEditResDto {
    private BackgroundTemplate backgroundTemplate;
    private PostitTemplate postitTemplate;

}
