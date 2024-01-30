package online.mokkoji.event.repository;

import online.mokkoji.event.domain.Event;
import online.mokkoji.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface EventRepository extends JpaRepository<Event, Long> {

    Event findBySessionId(String sessionId);

    List<Event> findAllByUser(User user);
}
