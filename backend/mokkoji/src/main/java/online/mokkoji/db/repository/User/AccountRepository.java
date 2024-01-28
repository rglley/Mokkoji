package online.mokkoji.db.repository.User;

import online.mokkoji.db.entity.User.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account save(Account account);
}
