package online.mokkoji.db.repository;

import online.mokkoji.db.entity.Event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

    Event findBySessionId(String sessionId);
}
