package online.mokkoji.result.service;

import online.mokkoji.event.dto.response.PhotoResDto;
import online.mokkoji.result.dto.request.RollingPaperReqDto;
import online.mokkoji.result.dto.response.MessageResDto;

import java.util.Map;

public interface ResultService {
    //create, read, update, delete
    Map<String, Object> getResultMap(String provider, String email);

    void createPhoto(PhotoResDto photoResDto);

    void createMessage(MessageResDto message);

    Map<String, Object> getPhotoAndMessageMap(Long resultId);

    void updateRollingpaper(Long resultId, RollingPaperReqDto rollingPaperReqDto);
}
