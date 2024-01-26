package online.mokkoji.db.repository;

import online.mokkoji.db.entity.Result.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
}
