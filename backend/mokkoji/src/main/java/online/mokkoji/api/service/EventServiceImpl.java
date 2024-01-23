package online.mokkoji.api.service;

import lombok.RequiredArgsConstructor;
import online.mokkoji.api.response.SessionObjectResDto;
import online.mokkoji.db.repository.EventRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public String createEvent(SessionObjectResDto sessionObject) {


        return null;
    }
}
