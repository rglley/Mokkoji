package online.mokkoji.common.auth.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import online.mokkoji.db.entity.Role;

@Getter
@Builder
public class SecurityUserDto {
    private String email;
    private String nickname;
    private String role;
    private Long userNo;

}
