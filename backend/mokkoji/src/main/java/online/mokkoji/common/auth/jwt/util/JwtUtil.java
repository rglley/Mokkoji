package online.mokkoji.common.auth.jwt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.common.auth.jwt.config.JwtConfig;
import org.springframework.security.oauth2.jwt.JwtException;
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
                .withClaim(PROVIDER_CLAIM, provider.toUpperCase())
                .withClaim(EMAIL_CLAIM, email)
                .sign(Algorithm.HMAC512(jwtConfig.getSecretKey()));
    }

    public String createRefreshToken() {
        Date now = new Date();
        return JWT.create()
                .withSubject(REFRESH_TOKEN_SUBJECT)
                .withExpiresAt(new Date(now.getTime() + jwtConfig.getRefreshExpiration()))
                .sign(Algorithm.HMAC512(jwtConfig.getSecretKey()));
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
            return Optional.ofNullable(JWT.require(Algorithm.HMAC512(jwtConfig.getSecretKey()))
                    .build()
                    .verify(accessToken)
                    .getClaim(PROVIDER_CLAIM)
                    .asString());
        } catch (Exception e) {
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
        if(extractAccessToken(req).isEmpty()) {
            throw new JwtException("accessToken을 찾을 수 없습니다.");
        }

        if(extractProvider(extractAccessToken(req).get()).isEmpty()) {
            throw new JwtException("provider를 찾을 수 없습니다.");
        }

        return extractProvider(extractAccessToken(req).get()).get();
    }

    public String getEmail(HttpServletRequest req) {
        if(extractAccessToken(req).isEmpty()) {
            throw new JwtException("accessToken을 찾을 수 없습니다.");
        }

        if(extractEmail(extractAccessToken(req).get()).isEmpty()) {
            throw new JwtException("email을 찾을 수 없습니다.");
        }

        return extractEmail(extractAccessToken(req).get()).get();
    }
}

