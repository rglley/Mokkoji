package online.mokkoji.result.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import online.mokkoji.result.domain.QPhoto;
import online.mokkoji.result.dto.response.PhotoPathResDto;

import java.util.List;
@RequiredArgsConstructor
public class PhotoRepositoryCustomImpl implements PhotoRepositoryCustom{

    private final JPAQueryFactory query;

    @Override
    public List<PhotoPathResDto> findPhotoPathListByResultId(Long resultId) {

        QPhoto photo = QPhoto.photo;

        return query.select(Projections.fields(PhotoPathResDto.class, photo.photoPath))
                .where(photo.result.id.eq(resultId))
                .from(photo)
                .fetch();
    }
}
