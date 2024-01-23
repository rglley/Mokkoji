package online.mokkoji.common.auth;

import lombok.Builder;
import lombok.Getter;
import online.mokkoji.common.auth.userinfo.GoogleOAuth2UserInfo;
import online.mokkoji.common.auth.userinfo.NaverOAuth2UserInfo;
import online.mokkoji.common.auth.userinfo.OAuth2Userinfo;
import online.mokkoji.db.entity.Role;
import online.mokkoji.db.entity.SocialType;
import online.mokkoji.db.entity.User;

import java.util.Map;
import java.util.UUID;

//소셜에서 받아오는 데이터를 담는 DTO
//소셜별로 받아오는 데이터가 달라 분기 처리 진행
@Getter
public class OAuthAttributes {
    private String nameAttributeKey;    //OAuth2 사용자 식별 기준 키(PK)
    private OAuth2Userinfo oAuth2UserInfo;  //소셜 타입별 로그인 유저 정보

    @Builder
    private OAuthAttributes(String nameAttributeKey, OAuth2Userinfo oauth2UserInfo) {
        this.nameAttributeKey = nameAttributeKey;
        this.oAuth2UserInfo = oauth2UserInfo;
    }

    //소셜에 맞는 OAuthAttributes 반환
    //회원 식별값, attributes, nameAttributeKey 저장, build
    public static OAuthAttributes of(SocialType socialType, String userNameAttributeName,
                                     Map<String, Object> attributes) {
        if(socialType == SocialType.NAVER) {
            return ofNaver(userNameAttributeName, attributes);
        }

        return ofGoogle(userNameAttributeName, attributes);
    }

    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oauth2UserInfo(new GoogleOAuth2UserInfo(attributes))
                .build();
    }

    public static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oauth2UserInfo(new NaverOAuth2UserInfo(attributes))
                .build();
    }

    //user entity 생성
    //role은 일단 GUEST, 추가 입력 받은 후 USER 변환
    public User toEntity(SocialType socialType, OAuth2Userinfo oauth2UserInfo) {
        return User.builder()
                .socialType(socialType)
                .socialId(oauth2UserInfo.getId())
                .name(oauth2UserInfo.getName())
                .email(UUID.randomUUID() + "@social.com")
                .image(oauth2UserInfo.getImageUrl())
                .role(Role.GUEST)
                .build();
    }
}
