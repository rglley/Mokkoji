package online.mokkoji.common.auth.oauth2.service;

import online.mokkoji.common.auth.oauth2.dto.response.UserInfoResDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.util.Map;

public interface OAuth2Service {

    HttpEntity<MultiValueMap<String, String>> generateTokenReq(String provider, String authorizationCode) throws Exception;

    HttpEntity<MultiValueMap<String, String>> generateProfileReq(String accessToken) throws Exception;

    Map<String, Object> getUserInfo(String provider, String accessToken) throws Exception;


}