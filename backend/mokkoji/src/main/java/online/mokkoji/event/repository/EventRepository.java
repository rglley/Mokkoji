package online.mokkoji.event.repository;

import online.mokkoji.event.domain.Event;
import online.mokkoji.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findBySessionId(String sessionId);

    // TODO : 이게 필요하면 유저에 연관관계 추가하는게 더 낫지 않을까
    List<Event> findAllByUser(User user);
}
