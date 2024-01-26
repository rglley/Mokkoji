package online.mokkoji.api.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import online.mokkoji.db.entity.Provider;
import online.mokkoji.db.entity.Role;
import online.mokkoji.db.entity.User;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class ModifyDto {

    @NotBlank
    private String provider;

    @NotBlank
    private String email;

    private String image;
    private String bank;
    private String accountNumber;

    public User toEntitiy(User user) {
        return User.builder()
                .provider(Provider.valueOf(provider))
                .email(email)
                .name(name)
                .image(image)
                .role(Role.USER)
                .build();
    }
}

