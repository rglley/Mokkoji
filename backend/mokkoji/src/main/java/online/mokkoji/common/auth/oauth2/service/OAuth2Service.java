package online.mokkoji.common.auth.oauth2.service;

import online.mokkoji.common.auth.oauth2.dto.response.UserInfoResDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.util.Map;

public interface OAuth2Service {

    //소셜 accessToken 요청 생성
    HttpEntity<MultiValueMap<String, String>> generateTokenReq(String provider, String authorizationCode) throws Exception;

    //프로필 정보 요청 생성
    HttpEntity<MultiValueMap<String, String>> generateProfileReq(String accessToken) throws Exception;

    //요청을 통해 회원 정보 반환
    Map<String, Object> getUserInfo(String provider, String accessToken) throws Exception;


}