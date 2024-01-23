package online.mokkoji.common.auth.userinfo;

import java.util.Map;

//소셜 타입별로 유저 정보를 가지는 추상클래스
public abstract class OAuth2Userinfo {
    protected Map<String, Object> attributes;

    public OAuth2Userinfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public abstract String getId(); //소셜 식별 값, 구글 : "sub" 네이버 : "id"
    public abstract String getName();
    public abstract String getImageUrl();
}
