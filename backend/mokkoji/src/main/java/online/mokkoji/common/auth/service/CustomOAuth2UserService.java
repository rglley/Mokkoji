package online.mokkoji.common.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.common.auth.response.CustomOAuth2User;
import online.mokkoji.common.auth.response.OAuthAttributes;
import online.mokkoji.db.entity.SocialType;
import online.mokkoji.db.entity.User;
import online.mokkoji.db.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;

    private static final String NAVER = "naver";

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("CustomOAuth2UserService.loadUser() 실행 - OAuth2 로그인 요청 진입");

        //DefaultOAuth2UserService의 loadUser 호출 : 소셜 로그인 API의 사용자 정보를 반환
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        //userRequest에서 필요 정보 추출
        String registrationId = userRequest.getClientRegistration().getRegistrationId();    //소셜 도메인명
        SocialType socialType = getSocialType(registrationId);
        String userNameAttribute = userRequest.getClientRegistration().getProviderDetails() //OAuth2 로그인 PK 값
                .getUserInfoEndpoint().getUserNameAttributeName();
        Map<String, Object> attributes = oAuth2User.getAttributes();    //소셜 로그인 API에서 넘어오는 userInfo(JSON)

        //OAuthAttributes 객체 생성
        OAuthAttributes extractAttributes = OAuthAttributes.of(socialType, userNameAttribute, attributes);

        User createdUser = getUser(extractAttributes, socialType);

        //추가 입력 정보를 포함한 CustomOAuth2User 객체 생성, 반환
        return new CustomOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(createdUser.getRole().getKEY())),
                attributes, extractAttributes.getNameAttributeKey(),
                createdUser.getEmail(), createdUser.getRole());
    }

    private SocialType getSocialType(String registrationId) {
        if(NAVER.equals(registrationId)) {
            return SocialType.NAVER;
        }

        return SocialType.GOOGLE;
    }

    //SocialType, Id로 회원 조회, 없으면 신규 회원 생성
    private User getUser(OAuthAttributes attributes, SocialType socialType) {
        User findUser = userRepository.findBySocialTypeAndSocialId(socialType,
                attributes.getOAuth2UserInfo().getId()).orElse(null);

        if(findUser == null)
            return saveUser(attributes, socialType);

        return findUser;
    }

    //entity 생성 후 DB에 저장
    private User saveUser(OAuthAttributes attributes, SocialType socialType) {
        User createUser = attributes.toEntity(socialType, attributes.getOAuth2UserInfo());
        return userRepository.save(createUser);
    }
}
