package online.mokkoji.db.repository;


import online.mokkoji.api.request.SignupDto;
import online.mokkoji.db.entity.Provider;
import online.mokkoji.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //회원가입
    User save(User user);

    Optional<User> findByEmail(String email);


    Optional<User> findByProviderAndEmail(Provider provider, String email);
}
