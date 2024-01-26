package online.mokkoji.common.auth.jwt;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import online.mokkoji.db.repository.JwtRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtRepository jwtRepository;

    @Transactional
    public void saveTokenInfo(JwtId jwtId, String refreshToken, String accessToken) {
        jwtRepository.save(new JwtToken(jwtId, accessToken, refreshToken));
    }

    @Transactional
    public void removeRefreshToken(String accessToken) {
        JwtToken token = jwtRepository.findByAccessToken(accessToken)
                .orElseThrow(IllegalArgumentException::new);

        jwtRepository.delete(token);
    }
}