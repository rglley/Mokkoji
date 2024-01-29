package online.mokkoji.user.repository;

import online.mokkoji.user.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<UserAccount, Long> {
    UserAccount save(UserAccount userAccount);
}
