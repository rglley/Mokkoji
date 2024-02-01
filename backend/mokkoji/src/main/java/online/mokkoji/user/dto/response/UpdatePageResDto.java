package online.mokkoji.user.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdatePageResDto {
    @NotBlank
    private String email;

    @NotBlank
    private String image;

    @NotBlank
    private String name;

    private String bank;
    private String accountNumber;

    @Builder
    public UpdatePageResDto(String email, String image, String name) {
        this.email = email;
        this.image = image;
        this.name = name;
    }
}
