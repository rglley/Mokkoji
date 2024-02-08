package online.mokkoji.result.repository;

import online.mokkoji.result.domain.RollingPaper.BackgroundTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BackgroundTemplateRepository extends JpaRepository<BackgroundTemplate, Integer> {

}
