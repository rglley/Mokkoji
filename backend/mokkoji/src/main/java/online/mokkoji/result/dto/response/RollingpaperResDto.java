package online.mokkoji.result.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import online.mokkoji.result.domain.BackgroundTemplate;
import online.mokkoji.result.domain.PostitTemplate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RollingpaperResDto {
    private BackgroundTemplate backgroundTemplate;
    private PostitTemplate postitTemplate;
}
