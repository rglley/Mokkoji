package online.mokkoji.common.auth.oauth2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import online.mokkoji.user.domain.Provider;

@Getter
@AllArgsConstructor
public class UserInfoResDto {
    private String provider;
    private String email;
    private String name;
    private String image;
    private boolean isFirst;
}
