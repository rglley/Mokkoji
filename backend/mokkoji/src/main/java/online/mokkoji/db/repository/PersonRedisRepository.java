package online.mokkoji.db.repository;

import online.mokkoji.db.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
public interface PersonRedisRepository extends CrudRepository<Person, String> {
}
