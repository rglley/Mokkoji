package online.mokkoji.api.service;

import lombok.RequiredArgsConstructor;
import online.mokkoji.api.request.SessionReqDto;
import online.mokkoji.db.entity.Event.Event;
import online.mokkoji.db.entity.User;
import online.mokkoji.db.repository.EventRepository;
import online.mokkoji.db.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private static final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    // 호스트 헹시(세션) 생성
    @Override
    public String createSession(SessionReqDto sessionDto) {

        User dump = new User("email", "name", "image");
        userRepository.save(dump);

        //User 객체 가져오기
        User user;
        // userId 없을 경우
        Optional<User> userById = userRepository.findById(sessionDto.getUserId());

        if (userById == null) {
            //예외 처리
//            throw new IllegalStateException("로그인을 해주세요");
            log.error("유저 아이디 없음");
        }
        // userId 제대로 있는 경우
        user = userById.get();


        // Event 객체 생성
        Event event = new Event(user, sessionDto.getSessionId(), sessionDto.getStartTime());

        // repository에 저장
        Event savedEvent = eventRepository.save(event);

        return savedEvent.getSessionId();
    }


    // 호스트의 세션 status closed로 변경
    @Override
    public void deleteSession(SessionReqDto sessionReqDto) {

        //sessionId로 session 찾기
        Event findSession = eventRepository.findBySessionId(sessionReqDto.getSessionId());

        //session의 status를 CLOSED로 변경
        findSession.closeSession(sessionReqDto);

        //session 저장
        eventRepository.save(findSession);
    }
}
