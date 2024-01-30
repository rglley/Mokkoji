package online.mokkoji.result.repository;

import online.mokkoji.result.domain.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
}
