package online.mokkoji.api.service;

import online.mokkoji.api.request.SessionReqDto;
import org.springframework.stereotype.Service;

@Service
public interface EventService {

    String createSession(SessionReqDto sessionReqDto);

    void deleteSession(SessionReqDto sessionDto);
}
