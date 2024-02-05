package online.mokkoji.result.repository;

import online.mokkoji.result.domain.RollingPaper.BackgroundTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BackgroundTemplateRepository extends JpaRepository<BackgroundTemplate, Long> {
    Optional<BackgroundTemplate> findById(Long bgId);
}
