package online.mokkoji.result.repository;

import online.mokkoji.result.domain.RollingPaper.PostitTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostitTemplateRepository extends JpaRepository<PostitTemplate, Integer> {
}
