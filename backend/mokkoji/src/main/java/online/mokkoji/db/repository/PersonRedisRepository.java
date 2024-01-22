package online.mokkoji.db.repository;

import online.mokkoji.db.entity.test.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRedisRepository extends CrudRepository<Person, String> {
}
