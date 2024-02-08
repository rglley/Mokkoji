package online.mokkoji.event.repository;

import online.mokkoji.event.domain.PhotoRedis;
import org.springframework.data.repository.CrudRepository;

public interface EventRedisRepository extends CrudRepository<PhotoRedis, String> {
}
