package online.mokkoji.user.repository;


import online.mokkoji.user.domain.Provider;
import online.mokkoji.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // TODO : 기본이니까 지워도 될 것 같음
//    User save(User user);
//
//    void delete(User user);

    Optional<User> findByRefreshToken(String refreshToken);

    Optional<User> findByEmail(String email);

    Optional<User> findByProviderAndEmail(Provider provider, String email);


}
