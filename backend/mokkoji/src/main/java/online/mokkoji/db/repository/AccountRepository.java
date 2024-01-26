package online.mokkoji.db.repository;

import online.mokkoji.db.entity.Account;
import online.mokkoji.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
