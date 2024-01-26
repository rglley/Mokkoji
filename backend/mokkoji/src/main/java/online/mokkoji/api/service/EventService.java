package online.mokkoji.api.service;

import online.mokkoji.api.request.SessionReqDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface EventService {

    // Session 생성 시 UserId 받기
    Long getUserId(Map<String, Object> params);

    // Session 생성
    String createSession(SessionReqDto sessionReqDto);

    // Session 닫기
    void deleteSession(String sessionId, SessionReqDto sessionReqDto);
}
