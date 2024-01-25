package online.mokkoji.db.repository;

import online.mokkoji.db.entity.Event.Event;
import online.mokkoji.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    Event findBySessionId(String sessionId);

    List<Event> findAllByUser(User user);

}
