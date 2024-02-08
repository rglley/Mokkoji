package online.mokkoji.result.repository;

import online.mokkoji.result.domain.RollingPaper.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
