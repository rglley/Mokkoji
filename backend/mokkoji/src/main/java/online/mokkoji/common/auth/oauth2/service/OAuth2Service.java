package online.mokkoji.common.auth.oauth2.service;

import online.mokkoji.common.auth.oauth2.dto.response.UserInfoResDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public interface OAuth2Service {
    UserInfoResDto getNaverUserInfo(String accessToken) throws Exception;

    HttpEntity<MultiValueMap<String, String>> generateNaverTokenReq(String authorizationCode) throws Exception;

    HttpEntity<MultiValueMap<String, String>> generateGoogleTokenReq(String authorizationCode) throws Exception;

    HttpEntity<MultiValueMap<String, String>> generateNaverProfileReq(String accessToken) throws Exception;

    ResponseEntity<String> requestNaverProfile(HttpEntity request) throws Exception;
}
