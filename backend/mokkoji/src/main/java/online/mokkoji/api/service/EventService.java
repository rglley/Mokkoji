package online.mokkoji.api.service;

import online.mokkoji.api.request.SessionDto;
import org.springframework.stereotype.Service;

@Service
public interface EventService {

    String createSession(SessionDto sessionDto);

    void deleteSession(String sessionId);
}
