package online.mokkoji.result.service;

import online.mokkoji.event.dto.response.PhotoResDto;
import online.mokkoji.result.dto.request.RollingPaperReqDto;
import online.mokkoji.result.dto.response.MessageResDto;
import online.mokkoji.result.dto.response.ResultResDto;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface ResultService {
    //create, read, update, delete
    Map<String, Object> getResultList(String provider, String email);

    ResultResDto getResult(Long resultId, Pageable pageable);

    void createPhoto(PhotoResDto photoResDto);

    void createMessage(MessageResDto message);

    Map<String, Object> getPhotoAndMessageMap(Long resultId);

    void updateRollingpaper(Long resultId, RollingPaperReqDto rollingPaperReqDto);
}
