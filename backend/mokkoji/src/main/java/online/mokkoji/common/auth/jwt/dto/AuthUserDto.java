package online.mokkoji.common.auth.jwt.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthUserDto {
    private String provider;
    private String email;
    private String role;
    private Long userNo;
}
