package online.mokkoji.db.repository;

import online.mokkoji.db.entity.Result.Result;
import online.mokkoji.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {

    List<Result> findAllByUser(User user);
}
