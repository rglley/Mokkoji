package online.mokkoji.result.repository;

import online.mokkoji.result.domain.RollingPaper.PostitTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostitRepository extends JpaRepository<PostitTemplate, Integer> {
}
