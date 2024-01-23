package online.mokkoji.api.service;

import online.mokkoji.api.response.SessionObjectResDto;
import org.springframework.stereotype.Service;

@Service
public interface EventService {

    String createEvent(SessionObjectResDto sessionObject);
}
