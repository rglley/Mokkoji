package online.mokkoji.common.auth.jwt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.common.auth.jwt.config.JwtConfig;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtUtil {
    private final JwtConfig jwtConfig;

    private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";
    private static final String REFRESH_TOKEN_SUBJECT = "RefreshToken";
    private static final String PROVIDER_CLAIM = "provider";
    private static final String EMAIL_CLAIM = "email";

    public boolean isTokenValid(String token) {
        try {
            JWT.require(Algorithm.HMAC512(jwtConfig.getSecretKey())).build().verify(token);
            return true;
        } catch (Exception e) {
            log.error("유효하지 않은 토큰입니다. {}", e.getMessage());
            return false;
        }
    }

    public String createAccessToken(String provider, String email) {
        log.info("AccessToken 생성");
        Date now = new Date();
        return JWT.create() //Jwt 토큰 빌더 반환 메서드
                .withSubject(ACCESS_TOKEN_SUBJECT)
                .withExpiresAt(new Date(now.getTime() + jwtConfig.getAccessExpiration()))
                .withClaim(PROVIDER_CLAIM, provider)
                .withClaim(EMAIL_CLAIM, email)
                .sign(Algorithm.HMAC512(jwtConfig.getSecretKey()));
    }

    public String createRefreshToken() {
        log.info("RefreshToken 생성");
        Date now = new Date();
        return JWT.create()
                .withSubject(REFRESH_TOKEN_SUBJECT)
                .withExpiresAt(new Date(now.getTime() + jwtConfig.getRefreshExpiration()))
                .sign(Algorithm.HMAC512(jwtConfig.getSecretKey()));
    }

    public void sendAccessToken(HttpServletResponse response, String accessToken) {
        response.setStatus(HttpServletResponse.SC_OK);

        response.setHeader(jwtConfig.getAccessHeader(), accessToken);
        log.info("AccessToken 재발급, 재발급된 Access Token : {}", accessToken);
    }

    public void sendAccessAndRefreshToken(HttpServletResponse response, String accessToken, String refreshToken) {
        response.setStatus(HttpServletResponse.SC_OK);

        setAccessTokenHeader(response, accessToken);
        setRefreshTokenHeader(response, refreshToken);
        log.info("회원 로그인, AccessToken, RefreshToken 발급 완료");
    }

    public Optional<String> extractRefreshToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(jwtConfig.getRefreshHeader()));
    }

    public Optional<String> extractAccessToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(jwtConfig.getAccessHeader()));
    }

    public Optional<String> extractProvider(String accessToken) {
        try {
            // 토큰 유효성 검사에 사용할 알고리즘이 있는 JWT verifier builder 반환
            return Optional.ofNullable(JWT.require(Algorithm.HMAC512(jwtConfig.getSecretKey()))
                    .build() //JWT verifier 생성
                    .verify(accessToken)
                    .getClaim(PROVIDER_CLAIM)
                    .asString());
        } catch (Exception e) {
            log.error("액세스 토큰이 유효하지 않습니다.");
            return Optional.empty();
        }
    }

    public Optional<String> extractEmail(String accessToken) {
        try {
            return Optional.ofNullable(JWT.require(Algorithm.HMAC512(jwtConfig.getSecretKey()))
                    .build()
                    .verify(accessToken)
                    .getClaim(EMAIL_CLAIM)
                    .asString());
        } catch (Exception e) {
            log.error("액세스 토큰이 유효하지 않습니다.");
            return Optional.empty();
        }
    }

    public void setAccessTokenHeader(HttpServletResponse response, String accessToken) {
        response.setHeader(jwtConfig.getAccessHeader(), accessToken);
    }

    public void setRefreshTokenHeader(HttpServletResponse response, String refreshToken) {
        response.setHeader(jwtConfig.getRefreshHeader(), refreshToken);
    }

    public String getProvider(HttpServletRequest req) {
        return extractProvider(extractAccessToken(req).get()).get();
    }

    public String getEmail(HttpServletRequest req) {
        return extractEmail(extractAccessToken(req).get()).get();
    }
}

