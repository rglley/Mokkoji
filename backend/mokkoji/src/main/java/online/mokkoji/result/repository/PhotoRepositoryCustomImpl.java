package online.mokkoji.result.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import online.mokkoji.result.domain.QPhoto;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
@RequiredArgsConstructor
public class PhotoRepositoryCustomImpl implements PhotoRepositoryCustom{

    private final JPAQueryFactory query;

    @Override
    @Cacheable(value = "photoPath", key = "#resultId", cacheManager = "cacheManager")
    public List<String> findPhotoPathListByResultId(Long resultId) {

        QPhoto photo = QPhoto.photo;

        return query.select(photo.photoPath)
                .where(photo.result.id.eq(resultId))
                .from(photo)
                .fetch();
    }
}
