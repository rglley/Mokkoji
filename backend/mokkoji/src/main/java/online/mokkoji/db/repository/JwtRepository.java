package online.mokkoji.db.repository;

import online.mokkoji.common.auth.jwt.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JwtRepository extends JpaRepository<JwtToken, String> {
    Optional<JwtToken> findByAccessToken(String accessToken);
}
