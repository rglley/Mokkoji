package online.mokkoji.result.repository;

import online.mokkoji.result.domain.RollingPaper.RollingPaper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RollingPaperRepository extends JpaRepository<RollingPaper, Long> {
}
