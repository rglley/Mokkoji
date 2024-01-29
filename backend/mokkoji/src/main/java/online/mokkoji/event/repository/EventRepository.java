package online.mokkoji.event.repository;

import online.mokkoji.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

    Event findBySessionId(String sessionId);

}
