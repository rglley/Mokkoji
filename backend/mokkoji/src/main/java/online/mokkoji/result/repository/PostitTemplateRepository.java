package online.mokkoji.result.repository;

import online.mokkoji.result.domain.RollingPaper.Message;
import online.mokkoji.result.domain.RollingPaper.PostitTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostitTemplateRepository extends JpaRepository<PostitTemplate, Long> {
    Optional<PostitTemplate> findById(Long postitId);
}
