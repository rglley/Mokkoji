package online.mokkoji.event.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.event.dto.request.RollingpaperReqDto;
import online.mokkoji.openvidu.dto.request.SessionReqDto;
import online.mokkoji.common.exception.RestApiException;
import online.mokkoji.common.exception.errorCode.OpenviduErrorCode;
import online.mokkoji.event.domain.Event;
import online.mokkoji.result.domain.Result;
import online.mokkoji.user.domain.User;
import online.mokkoji.event.repository.EventRepository;
import online.mokkoji.event.repository.ResultRepository;
import online.mokkoji.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class EventServiceImpl implements EventService {


    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final ResultRepository resultRepository;

    //userId 받기
    @Override
    public Long getUserId(Map<String, Object> params) {
        if (!params.containsKey("userId")) {
            log.error("유저 아이디 없음");
            throw new RestApiException(OpenviduErrorCode.NO_USER_ID);
        }

        Long userId = (Long) params.get("userId");
        return userId;
    }

    // 호스트 Session 생성
    @Override
    public String createSession(SessionReqDto sessionDto) {

        User dump = new User("email", "name", "image");
        userRepository.save(dump);

        //User 객체 가져오기
        User user;
        // userId 없을 경우
        Optional<User> userById = userRepository.findById(sessionDto.getUserId());

        //예외 처리
        if (userById == null || userById.isEmpty()) {
            log.error("유저 아이디 없음");
            throw new RestApiException(OpenviduErrorCode.NO_USER_ID);
        }

        // userId 제대로 있는 경우
        user = userById.get();


        // Event 객체 생성
        Event event = new Event(user, sessionDto.getSessionId(), sessionDto.getStartTime());

        // repository에 저장
        Event savedEvent = eventRepository.save(event);

        // 빈 Result도 생성
        Result result = new Result(savedEvent);
        resultRepository.save(result);

        return savedEvent.getSessionId();
    }


    // 호스트의 세션 status closed로 변경
    @Override
    public void deleteSession(String sessionId, SessionReqDto sessionReqDto) {

        // 세션의 호스트Id와 지금 전달받은 userId가 맞는지 확인
        Event event = eventRepository.findBySessionId(sessionId);
        if (event.getUser().getId() != sessionReqDto.getUserId()) {
            log.error("호스트Id가 아님"); //임시로 하는 거.
            throw new RestApiException(OpenviduErrorCode.NOT_HOST_USER_ID);
        }

        //sessionId로 session 찾기
        Event findSession = eventRepository.findBySessionId(sessionId);

        //session의 status를 CLOSED로 변경
        findSession.closeSession(sessionReqDto);

        //session 저장
        Event savedEvent = eventRepository.save(findSession);


    }

    // 롤링페이퍼 파일 받아서 유효성 검사
    @Override
    public Map<String, Map> createRollingpaperFileMap(RollingpaperReqDto rollingpaperReqDto) {

        // resonse용 map
        Map<String, Map> returnMap = new HashMap<>();
        Map<String, String> textMap = new HashMap<>();

        // text가 있는 경우 map에 저장
        String text = rollingpaperReqDto.getText();
        if (text != null) {
            if (!text.isBlank()) {
                textMap.put("text", text);
            }
        }


        Map<String, MultipartFile> fileMap = new HashMap<>();
        // 음성이 있는 경우 map에 저장
        // TODO : ispresent 사용, 서비스로 null처리 이동 다 dto에 담아서
        MultipartFile voice = rollingpaperReqDto.getVoice();
        if (voice != null && !voice.isEmpty()) {
            fileMap.put("voice", voice);
        }
        // 영상이 있는 경우 map에 저장
        MultipartFile video = rollingpaperReqDto.getVideo();
        if (video != null && !video.isEmpty()) {
            fileMap.put("video", video);
        }

        returnMap.put("files", fileMap);
        returnMap.put("text", textMap);

        return returnMap;
    }
}
