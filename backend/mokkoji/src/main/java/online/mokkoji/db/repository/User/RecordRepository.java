package online.mokkoji.db.repository.User;

import online.mokkoji.db.entity.User.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {

}
