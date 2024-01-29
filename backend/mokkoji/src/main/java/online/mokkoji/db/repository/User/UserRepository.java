package online.mokkoji.db.repository.User;


import online.mokkoji.db.entity.User.Provider;
import online.mokkoji.db.entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);

    void delete(User user);

    Optional<User> findByRefreshToken(String refreshToken);

    Optional<User> findByEmail(String email);

    Optional<User> findByProviderAndEmail(Provider provider, String email);
}
