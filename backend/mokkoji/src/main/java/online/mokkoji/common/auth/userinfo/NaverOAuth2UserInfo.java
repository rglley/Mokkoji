package online.mokkoji.common.auth.userinfo;

import java.util.Map;

public class NaverOAuth2UserInfo extends OAuth2Userinfo {
    private final Map<String, Object> RESPONSE = (Map<String, Object>) attributes.get("response");

    public NaverOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        if(RESPONSE == null) {
            return null;
        }

        return (String) RESPONSE.get("id");
    }

    @Override
    public String getName() {
        if(RESPONSE == null) {
            return null;
        }

        return (String) RESPONSE.get("name");
    }

    @Override
    public String getImageUrl() {
        if(RESPONSE == null) {
            return null;
        }

        return (String) RESPONSE.get("profile_image");
    }
}
