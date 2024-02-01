package online.mokkoji.common.auth.oauth2.controller;

import lombok.RequiredArgsConstructor;
import online.mokkoji.common.auth.oauth2.OAuth2Config;
import online.mokkoji.common.auth.oauth2.dto.response.UserInfoResDto;
import online.mokkoji.common.auth.oauth2.service.OAuth2Service;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth2")
public class OAuth2Controller {

    private final OAuth2Service oAuth2Service;

    @GetMapping("/{provider}/{code}")
    public ResponseEntity<UserInfoResDto> getUserInfo(@PathVariable String provider, @PathVariable String code) throws Exception {
        if(provider.equals("naver")) {
           UserInfoResDto result = oAuth2Service.getGoogleUserInfo(code);

           return new ResponseEntity<>(result, HttpStatus.OK);
        }

        return null;
    }
}
