package online.mokkoji.result.repository;

import online.mokkoji.result.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findAllByResultId(Long resultId);
}
