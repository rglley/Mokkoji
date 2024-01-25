package online.mokkoji.api.service;

import online.mokkoji.api.request.SessionReqDto;
import org.springframework.stereotype.Service;

@Service
public interface EventService {

    // Session 생성
    String createSession(SessionReqDto sessionReqDto);

    // Session 닫기
    void deleteSession(SessionReqDto sessionDto);
}
