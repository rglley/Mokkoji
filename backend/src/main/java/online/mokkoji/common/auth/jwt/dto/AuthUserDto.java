package online.mokkoji.common.auth.jwt.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthUserDto {
    private String provider;
    private String email;
    private String role;
    private Long userNo;

    @Builder
    public AuthUserDto(String provider, String email, String role, Long userNo) {
        this.provider = provider;
        this.email = email;
        this.role = role;
        this.userNo = userNo;
    }
}
