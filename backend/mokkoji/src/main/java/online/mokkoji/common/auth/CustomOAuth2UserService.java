package online.mokkoji.common.auth;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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

//로그인 이후 사용자 정보 기반 가입, 정보수정, 세션 저장 등 기능 지원
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository USER_REPOSITORY;
    private final HttpSession HTTP_SESSION;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        //로그인 진행 서비스 구분(구글, 네이버)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        //OAuth2 로그인 진행 키가 되는 필드값(Primary Key)
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint()
                    .getUserNameAttributeName();
        
        //OAuth2UserService로 가져온 OAuth2User의 attribute를 담을 클래스
        OAuthAttributes attributes = OAuthAttributes
            .of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        
        //SessionUser : 세션에 사용자 정보를 저장하기 위한 Dto 클래스
        User user = saveOrUpdate(attributes);
        HTTP_SESSION.setAttribute("user", new SessionUser(user));
        
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );

    }
    
    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = USER_REPOSITORY.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());


        return USER_REPOSITORY.save(user);
    }
}
