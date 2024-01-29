package online.mokkoji.common.auth.oauth2.attribute;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import online.mokkoji.user.domain.Provider;

import java.util.HashMap;
import java.util.Map;

//OAuth2 인증으로 얻어온 사용자 정보를 Map 형태로 반환받기 위해 사용하는 클래스
//소셜별로 받아오는 데이터가 달라 분기 처리를 담당
@Getter
@Builder(access = AccessLevel.PRIVATE)
public class OAuth2Attribute {
    private Map<String, Object> attributes;
    private String attributeKey;    //OAuth2 로그인 진행의 PK
    private Provider provider;
    private String email;
    private String name;
    private String image;

    public static OAuth2Attribute of(String provider,
                                     String attributeKey, Map<String, Object> attributes) {
        if (provider == "naver") {
            return ofNaver(provider, "id", attributes);
        }

        return ofGoogle(provider, attributeKey, attributes);
    }

    public static OAuth2Attribute ofNaver(String provider, String attributeKey, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuth2Attribute.builder()
                .attributeKey(attributeKey)
                .provider(Provider.valueOf(provider))
                .email((String) response.get("email"))
                .name((String) response.get("name"))
                .image((String) response.get("profile_image"))
                .attributes(response)
                .build();

    }

    public static OAuth2Attribute ofGoogle(String provider, String attributeKey, Map<String, Object> attributes) {
        return OAuth2Attribute.builder()
                .attributeKey(attributeKey)
                .provider(Provider.valueOf(provider))
                .email((String) attributes.get("email"))
                .name((String) attributes.get("name"))
                .image((String) attributes.get("picture"))
                .attributes(attributes)
                .build();
    }

    public Map<String, Object> convertToMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", attributeKey);
        map.put("key", attributeKey);
        map.put("provider", provider);
        map.put("email", email);
        map.put("name", name);
        map.put("image", image);
        return map;
    }
}
