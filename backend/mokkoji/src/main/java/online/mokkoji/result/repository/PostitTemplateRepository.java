package online.mokkoji.result.repository;

import online.mokkoji.result.domain.RollingPaper.PostitName;
import online.mokkoji.result.domain.RollingPaper.PostitTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostitTemplateRepository extends JpaRepository<PostitTemplate, Integer> {
    Optional<PostitTemplate> findByPostitName(PostitName postitName);
}
