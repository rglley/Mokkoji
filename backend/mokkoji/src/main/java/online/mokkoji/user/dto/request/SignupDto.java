package online.mokkoji.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class SignupDto {

    @NotBlank
    private String name;

    @NotBlank
    private String image;

    private String bank;
    private String accountNumber;
}
