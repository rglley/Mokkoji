package online.mokkoji.common.auth.oauth2.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import online.mokkoji.common.auth.oauth2.OAuth2Config;
import online.mokkoji.common.auth.oauth2.dto.response.UserInfoResDto;
import online.mokkoji.common.exception.RestApiException;
import online.mokkoji.common.exception.errorCode.CommonErrorCode;
import online.mokkoji.common.exception.errorCode.OAuthErrorCode;
import online.mokkoji.user.domain.Provider;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class Oauth2ServiceImpl implements OAuth2Service {

    private final OAuth2Config oAuth2Config;
    private final ObjectMapper objectMapper;

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
        if(!tokenJSON.has("accessToken"))
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

        String accessToken = tokenJSON.get("accessToken").asText();

        HttpEntity<MultiValueMap<String, String>> profileRequest = generateNaverProfileReq(accessToken);

        restTemplate = new RestTemplate();

        ResponseEntity<String> profileResponse = restTemplate.exchange(
                oAuth2Config.getNaverProfileUrl(), HttpMethod.POST, profileRequest, String.class
        );

        JsonNode profileJSON = objectMapper.readTree(profileResponse.getBody());
        if(!(tokenJSON.has("email") && tokenJSON.has("name")
                && tokenJSON.has("profile_image")))
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);

        String email = profileJSON.get("email").asText();
        String name = profileJSON.get("name").asText();
        String image = profileJSON.get("profile_image").asText();

        return new UserInfoResDto("naver", email, name, image);
    }

    private HttpEntity<MultiValueMap<String, String>> generateNaverTokenReq(String authorizationCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", oAuth2Config.getNaverId());
        params.add("client_secret", oAuth2Config.getNaverSecret());
        params.add("redirect_uri", UUID.randomUUID().toString());
        params.add("code", authorizationCode);
        return new HttpEntity<>(params, headers);
    }

    private HttpEntity<MultiValueMap<String, String>> generateGoogleTokenReq(String authorizationCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", oAuth2Config.getNaverId());
        params.add("client_secret", oAuth2Config.getNaverSecret());
        params.add("state", UUID.randomUUID().toString());
        params.add("code", authorizationCode);
        return new HttpEntity<>(params, headers);
    }

    private HttpEntity<MultiValueMap<String, String>> generateNaverProfileReq(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        return new HttpEntity<>(headers);
    }

    private ResponseEntity<String> requestNaverProfile(HttpEntity request) {
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.exchange(oAuth2Config.getNaverProfileUrl(), HttpMethod.POST, request, String.class);
    }
}
