package online.mokkoji.api.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RollingpaperReqDto {

    @NotNull
    private Long userId;
    @Size(max = 100)
    private String text;
    private MultipartFile voice;
    private MultipartFile video;
}
