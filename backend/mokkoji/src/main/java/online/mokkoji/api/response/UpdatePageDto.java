package online.mokkoji.api.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class UpdatePageDto {
    @NotBlank
    private String email;

    @NotBlank
    private String image;

    @NotBlank
    private String name;

    private String bank;
    private String accountNumber;
}
