package online.mokkoji.api.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PhotoReqDto {

    @NotNull
    private MultipartFile photo;
    @NotNull
    private Long userId;

}
