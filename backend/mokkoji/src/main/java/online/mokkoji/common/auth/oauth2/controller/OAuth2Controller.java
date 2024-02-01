package online.mokkoji.common.auth.oauth2.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import online.mokkoji.common.auth.jwt.util.JwtUtil;
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
    private final JwtUtil jwtUtil;

    @GetMapping("/{provider}/{code}")
    public ResponseEntity<UserInfoResDto> getUserInfo(@PathVariable String provider, @PathVariable String code,
                                                      HttpServletResponse res) throws Exception {
        if(provider.equals("naver")) {
           UserInfoResDto userInfoResDto = oAuth2Service.getNaverUserInfo(code);

           String email = userInfoResDto.getEmail();
           String accessToken = jwtUtil.createAccessToken("naver", email);

           if(userInfoResDto.isFirst()) {
               jwtUtil.sendAccessToken(res, accessToken);
               return new ResponseEntity<>(userInfoResDto, HttpStatus.OK);
           }

           String refreshToken = jwtUtil.createRefreshToken();
           jwtUtil.sendAccessAndRefreshToken(res, accessToken, refreshToken);
           return new ResponseEntity<>(userInfoResDto, HttpStatus.OK);
        }

        return null;
    }
}
