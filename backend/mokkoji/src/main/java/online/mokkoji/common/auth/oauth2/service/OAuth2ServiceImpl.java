package online.mokkoji.common.auth.oauth2.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.common.auth.oauth2.config.OAuth2Config;
import online.mokkoji.common.auth.oauth2.dto.response.UserInfoResDto;
import online.mokkoji.common.exception.RestApiException;
import online.mokkoji.common.exception.errorCode.CommonErrorCode;
import online.mokkoji.common.exception.errorCode.OAuthErrorCode;
import online.mokkoji.user.domain.Authority;
import online.mokkoji.user.domain.Provider;
import online.mokkoji.user.domain.User;
import online.mokkoji.user.repository.UserRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2ServiceImpl implements OAuth2Service {

    private final OAuth2Config oAuth2Config;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    @Override
    public UserInfoResDto getNaverUserInfo(String authorizationCode) throws Exception {
        if(authorizationCode == null)
            throw new RestApiException(OAuthErrorCode.INVALID_AUTHORIZATION_CODE);

        HttpEntity<MultiValueMap<String, String>> tokenRequest = generateNaverTokenReq(authorizationCode);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> tokenResponse = restTemplate.exchange(
                oAuth2Config.getNaverTokenUrl(), HttpMethod.POST, tokenRequest, String.class
        );

        JsonNode tokenJSON = objectMapper.readTree(tokenResponse.getBody());
        if(!tokenJSON.has("access_token"))
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

        String accessToken = tokenJSON.get("access_token").asText();

        HttpEntity<MultiValueMap<String, String>> profileRequest = generateNaverProfileReq(accessToken);

        restTemplate = new RestTemplate();

        ResponseEntity<String> profileResponse = restTemplate.exchange(
                oAuth2Config.getNaverProfileUrl(), HttpMethod.POST, profileRequest, String.class
        );

        JsonNode profileJSON = objectMapper.readTree(profileResponse.getBody());
        log.info(profileJSON.toString());

        String email = profileJSON.path("response").path("email").asText();
        String name = profileJSON.path("response").path("name").asText();
        String image = profileJSON.path("response").path("profile_image").asText();


        Optional<User> findUser = userRepository.findByProviderAndEmail(Provider.NAVER, email);

        if(findUser.isEmpty()) {
            User guestUser = User.builder()
                    .authority(Authority.GUEST)
                    .provider("NAVER")
                    .email(email)
                    .name(name)
                    .image(image)
                    .build();

            return new UserInfoResDto("naver", email, name, image, true);
        }

        return new UserInfoResDto("naver", email, name, image, false);
    }

    @Override
    public HttpEntity<MultiValueMap<String, String>> generateNaverTokenReq(String authorizationCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", oAuth2Config.getNaverId());
        params.add("client_secret", oAuth2Config.getNaverSecret());
        params.add("state", "mokkoji");
        params.add("code", authorizationCode);
        return new HttpEntity<>(params, headers);
    }

    @Override
    public HttpEntity<MultiValueMap<String, String>> generateGoogleTokenReq(String authorizationCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", oAuth2Config.getNaverId());
        params.add("client_secret", oAuth2Config.getNaverSecret());
        params.add("state", "mokkoji");
        params.add("code", authorizationCode);
        return new HttpEntity<>(params, headers);
    }

    @Override
    public HttpEntity<MultiValueMap<String, String>> generateNaverProfileReq(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        return new HttpEntity<>(headers);
    }

    @Override
    public ResponseEntity<String> requestNaverProfile(HttpEntity request) {
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.exchange(oAuth2Config.getNaverProfileUrl(), HttpMethod.POST, request, String.class);
    }
}
