package online.mokkoji.common.auth;

import lombok.Getter;
import online.mokkoji.db.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collection;
import java.util.Map;

//OAuth2UserService에서 반환되는 객체
//소셜 로그인에서 제공되는 정보 외 추가 입력 정보 저장을 위해 custom
@Getter
public class CustomOAuth2User extends DefaultOAuth2User {
    //추가 정보
    private String email;
    private Role role;

    public CustomOAuth2User(Collection<? extends GrantedAuthority> authorities,
                            Map<String, Object> attributes, String nameAttributeKey,
                            String email, Role role) {
        super(authorities, attributes, nameAttributeKey);   //DefaultOAuth2User 생성 + 추가 정보
        this.email = email;
        this.role = role;
    }
}
