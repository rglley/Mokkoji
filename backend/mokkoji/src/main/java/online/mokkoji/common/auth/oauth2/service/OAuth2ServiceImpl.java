package online.mokkoji.common.auth.oauth2.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.common.auth.jwt.util.JwtUtil;
import online.mokkoji.common.auth.oauth2.config.OAuth2Config;
import online.mokkoji.common.auth.oauth2.dto.response.UserInfoResDto;
import online.mokkoji.common.exception.RestApiException;
import online.mokkoji.common.exception.errorcode.CommonErrorCode;
import online.mokkoji.common.exception.errorcode.OAuthErrorCode;
import online.mokkoji.user.domain.Authority;
import online.mokkoji.user.domain.Provider;
import online.mokkoji.user.domain.User;
import online.mokkoji.user.repository.UserRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2ServiceImpl implements OAuth2Service {

    private final OAuth2Config oAuth2Config;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public Map<String, Object> getNaverUserInfo(String authorizationCode) throws Exception {
        if(authorizationCode == null)
            throw new RestApiException(OAuthErrorCode.INVALID_AUTHORIZATION_CODE);

        HttpEntity<MultiValueMap<String, String>> tokenRequest = generateNaverTokenReq(authorizationCode);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> tokenResponse = restTemplate.exchange(
                oAuth2Config.getNaverTokenUrl(), HttpMethod.POST, tokenRequest, String.class
        );

        log.info(oAuth2Config.getNaverTokenUrl());

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

        String email = profileJSON.path("response").path("email").asText();
        String name = profileJSON.path("response").path("name").asText();
        String image = profileJSON.path("response").path("profile_image").asText();

        Map<String, Object> resultMap = new HashMap<>();

        String createdAccessToken = jwtUtil.createAccessToken("NAVER", email);
        resultMap.put("accessToken", createdAccessToken);

        Optional<User> findUser = userRepository.findByProviderAndEmail(Provider.NAVER, email);

        if(findUser.isEmpty()) {
            User guestUser = User.builder()
                    .authority(Authority.GUEST)
                    .email(email)
                    .image(image)
                    .name(name)
                    .provider(Provider.NAVER)
                    .build();

            userRepository.save(guestUser);

            UserInfoResDto userInfoResDto = new UserInfoResDto("naver", email, name, image, true);

            resultMap.put("userInfo", userInfoResDto);

            return resultMap;
        }

        String refreshToken = jwtUtil.createRefreshToken();

        User loginUser = findUser.get();
        loginUser.updateRefreshToken(refreshToken);
        userRepository.save(loginUser);

        UserInfoResDto userInfoResDto = new UserInfoResDto("naver", email, name, image, false);

        resultMap.put("refreshToken", refreshToken);
        resultMap.put("userInfo", userInfoResDto);

        return resultMap;
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
    public HttpEntity<MultiValueMap<String, String>> generateNaverProfileReq(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        return new HttpEntity<>(headers);
    }

    @Override
    public HttpEntity<MultiValueMap<String, String>> generateTokenReq(String provider, String authorizationCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("code", authorizationCode);
        params.add("state", "mokkoji");

        if(provider.equals("naver")) {
            log.info("네이버 파람 추가");
            params.add("client_id", oAuth2Config.getNaverId());
            params.add("client_secret", oAuth2Config.getNaverSecret());
        }

        else if(provider.equals("google")) {
            params.add("client_id", oAuth2Config.getGoogleId());
            params.add("client_secret", oAuth2Config.getGoogleSecret());
            params.add("redirect_uri", oAuth2Config.getGoogleRedirectUri());
        }

        return new HttpEntity<>(params, headers);
    }

    @Override
    public HttpEntity<MultiValueMap<String, String>> generateProfileReq(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        return new HttpEntity<>(headers);
    }

    @Override
    public Map<String, Object> getUserInfo(String provider, String authorizationCode) throws Exception {
        if(authorizationCode == null)
            throw new RestApiException(OAuthErrorCode.INVALID_AUTHORIZATION_CODE);

        String tokenUrl = "";
        String profileUrl = "";

        if(provider.equals("naver")) {
            tokenUrl = oAuth2Config.getNaverTokenUrl();
            profileUrl = oAuth2Config.getNaverProfileUrl();
        }
        else if(provider.equals("google")) {
            tokenUrl = oAuth2Config.getGoogleTokenUrl();
            profileUrl = oAuth2Config.getGoogleProfileUrl();
        }

        log.info("네이버? 구글? {}", provider);

        HttpEntity<MultiValueMap<String, String>> tokenRequest = generateTokenReq(provider, authorizationCode);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> tokenResponse = restTemplate.exchange(
                tokenUrl, HttpMethod.POST, tokenRequest, String.class
        );

        log.info("토큰 요청 전송 {}", tokenUrl);

        JsonNode tokenJSON = objectMapper.readTree(tokenResponse.getBody());

        if(!tokenJSON.has("access_token"))
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

        String accessToken = tokenJSON.get("access_token").asText();

        HttpEntity<MultiValueMap<String, String>> profileRequest = generateProfileReq(accessToken);

        restTemplate = new RestTemplate();
        ResponseEntity<String> profileResponse = restTemplate.exchange(
                profileUrl, HttpMethod.POST, profileRequest, String.class
        );

        log.info("프로필 요청 전송 {}", profileUrl);

        JsonNode profileJSON = objectMapper.readTree(profileResponse.getBody());

        String email = "";
        String name = "";
        String image = "";
        String jwtAccessToken = "";

        if(provider.equals("naver")) {
            email = profileJSON.path("response").path("email").asText();
            name = profileJSON.path("response").path("name").asText();
            image = profileJSON.path("response").path("profile_image").asText();
            jwtAccessToken = jwtUtil.createAccessToken("NAVER", email);
        }
        else if(provider.equals("google")) {
            email = profileJSON.path("email").asText();
            name = profileJSON.path("name").asText();
            image = profileJSON.path("picture").asText();
            jwtAccessToken = jwtUtil.createAccessToken("GOOGLE", email);
        }

        log.info("회원정보 가져왔어~");

        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("accessToken", jwtAccessToken);

        Optional<User> findUser = userRepository.findByProviderAndEmail(Provider.valueOf(provider.toUpperCase()), email);

        if(findUser.isEmpty()) {
            UserInfoResDto userInfoResDto = new UserInfoResDto(provider, email, name, image, true);

            resultMap.put("userInfo", userInfoResDto);

            return resultMap;
        }

        String refreshToken = jwtUtil.createRefreshToken();

        User loginUser = findUser.get();
        loginUser.updateRefreshToken(refreshToken);
        userRepository.save(loginUser);

        UserInfoResDto userInfoResDto = new UserInfoResDto(provider, email, name, image, false);

        resultMap.put("refreshToken", refreshToken);
        resultMap.put("userInfo", userInfoResDto);

        return resultMap;
    }
}