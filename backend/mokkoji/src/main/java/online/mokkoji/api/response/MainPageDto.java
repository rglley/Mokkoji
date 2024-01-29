package online.mokkoji.api.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class MainPageDto {
    @NotBlank
    private String image;

    @NotBlank
    private String name;
}
