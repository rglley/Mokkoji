package online.mokkoji.result.repository;

import online.mokkoji.result.dto.response.PhotoPathResDto;

import java.util.List;

public interface PhotoRepositoryCustom {

    List<PhotoPathResDto> findPhotoPathListByResultId(Long resultId);
}
