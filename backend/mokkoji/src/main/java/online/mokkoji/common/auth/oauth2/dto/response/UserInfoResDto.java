package online.mokkoji.common.auth.oauth2.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import online.mokkoji.user.domain.Provider;

@Getter
@AllArgsConstructor
public class UserInfoResDto {

    @NotBlank
    private String provider;

    @NotNull
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String image;

    @NotNull
    private boolean isFirst;
}
