package online.mokkoji.common.auth.jwt;

import lombok.Builder;
import lombok.Getter;
import online.mokkoji.db.entity.User.Role;

@Getter
@Builder
public class AuthUserDto {
    private String provider;
    private String email;
    private String role;
    private Long userNo;
}
