package online.mokkoji.common.auth.oauth2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.common.auth.oauth2.OAuth2Attribute;
import online.mokkoji.db.entity.Provider;
import online.mokkoji.db.entity.User;
import online.mokkoji.db.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

//OAuth2.0 인증을 통해 사용자 정보를 가져오는 역할(서비스)
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements
        OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("사용자 정보를 불러옵니다.");
        // 기본 OAuth2UserService 객체 생성
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();

        // OAuth2UserService를 사용하여 OAuth2User 정보를 가져온다.
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        // 클라이언트 등록 ID(google, naver)와 사용자 이름 속성을 가져온다.
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();


        // OAuth2UserService를 사용하여 가져온 OAuth2User 정보로 OAuth2Attribute 객체를 만든다.
        OAuth2Attribute oAuth2Attribute =
                OAuth2Attribute.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        // OAuth2Attribute의 속성값들을 Map으로 반환 받는다.
        Map<String, Object> userAttribute = oAuth2Attribute.convertToMap();

        // 사용자 email(또는 id) 정보를 가져온다.
        String email = (String) userAttribute.get("email");
        Provider provider = Provider.valueOf((String) userAttribute.get("provider"));
        // 이메일로 가입된 회원인지 조회한다.
        Optional<User> findUser = userRepository.findByProviderAndEmail(provider, email);

        if (findUser.isEmpty()) {
            log.info("존재하지 않는 회원입니다. 게스트 권한을 부여합니다.");
            // 회원이 존재하지 않을경우, userAttribute의 exist 값을 false로 넣어준다.
            userAttribute.put("exist", false);
            DefaultOAuth2User defaultOAuth2User = new DefaultOAuth2User(
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_GUEST")),
                    userAttribute, "email");
            // 회원의 권한(회원이 존재하지 않으므로 기본권한인 ROLE_USER를 넣어준다), 회원속성, 속성이름을 이용해 DefaultOAuth2User 객체를 생성해 반환한다.
            return defaultOAuth2User;
        }

        // 회원이 존재할경우, userAttribute의 exist 값을 true로 넣어준다.
        log.info("존재하는 회원입니다.");
        userAttribute.put("exist", true);
        DefaultOAuth2User defaultOAuth2User = new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(findUser.get().getRole().getKey())),
                userAttribute, "email");
        // 회원의 권한과, 회원속성, 속성이름을 이용해 DefaultOAuth2User 객체를 생성해 반환한다.
        return defaultOAuth2User;
    }

}
