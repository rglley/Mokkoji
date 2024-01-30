package online.mokkoji.common.auth.oauth2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.common.auth.oauth2.attribute.OAuth2Attribute;
import online.mokkoji.user.domain.Provider;
import online.mokkoji.user.domain.User;
import online.mokkoji.user.repository.UserRepository;
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
        log.info("OAuth2 로그인 요청, 사용자 정보를 불러옵니다.");
        //DefaultOAuth2UserService 객체 생성
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();

        //OAuth2 서비스에서 가져온 사용자 정보
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        // Provider와 사용자 이름 속성(nameAttributeKey가 될 값)을 가져온다.
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();


        //OAuth2User 정보로 OAuth2Attribute 객체 생성, Map으로 변환
        OAuth2Attribute oAuth2Attribute =
                OAuth2Attribute.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        Map<String, Object> attributes = oAuth2Attribute.convertToMap();

        Provider provider = Provider.valueOf((String) attributes.get("provider"));
        String email = (String) attributes.get("email");

        //provider, email로 회원 조회
        Optional<User> findUser = userRepository.findByProviderAndEmail(provider, email);

        if (findUser.isEmpty()) {
            log.info("존재하지 않는 회원입니다. 게스트 권한을 부여합니다.");
            attributes.put("exist", false);
            DefaultOAuth2User defaultOAuth2User = new DefaultOAuth2User(
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_GUEST")),
                    attributes, "email");
            //회원속성, 속성이름을 이용해 DefaultOAuth2User 객체를 생성해 반환
            return defaultOAuth2User;
        }

        // 회원이 존재할경우, userAttribute의 exist 값을 true로 넣어준다.
        log.info("존재하는 회원입니다.");
        attributes.put("exist", true);
        DefaultOAuth2User defaultOAuth2User = new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(findUser.get().getAuthority().getKey())),
                attributes, "email");
        // 회원의 권한과, 회원속성, 속성이름을 이용해 DefaultOAuth2User 객체를 생성해 반환한다.
        return defaultOAuth2User;
    }


}
