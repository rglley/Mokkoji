package online.mokkoji.event.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.common.exception.RestApiException;
import online.mokkoji.common.exception.errorCode.OpenviduErrorCode;
import online.mokkoji.event.domain.Event;
import online.mokkoji.event.dto.request.RollingpaperReqDto;
import online.mokkoji.event.repository.EventRepository;
import online.mokkoji.result.domain.RollingPaper;
import online.mokkoji.result.repository.ResultRepository;
import online.mokkoji.openvidu.dto.request.SessionReqDto;
import online.mokkoji.result.domain.Result;
import online.mokkoji.result.repository.RollingPaperRepository;
import online.mokkoji.user.domain.User;
import online.mokkoji.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class EventServiceImpl implements EventService {


    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final ResultRepository resultRepository;
    private final RollingPaperRepository rollingPaperRepository;

    //userId 받기
    @Override
    public Long getUserId(Map<String, Object> params) {
        if (!params.containsKey("userId")) {
            log.error("유저 아이디 없음");
            throw new RestApiException(OpenviduErrorCode.NO_USER_ID);
        }

        return (Long) params.get("userId");
    }

    // 호스트 Session 생성
    @Override
    public String createSession(SessionReqDto sessionDto) {

        //User 객체 가져오기
        // userId 없을 경우
        User user = userRepository.findById(sessionDto.getUserId())
                .orElseThrow(() -> new RestApiException(OpenviduErrorCode.NO_USER_ID));

        // Event 객체 생성
        Event event = Event.createSession()
                .user(user)
                .sessionId(sessionDto.getSessionId())
                .startTime(sessionDto.getStartTime())
                .build();

        // repository에 저장
        Event savedEvent = eventRepository.save(event);

        // 빈 Result도 생성
        Result result = new Result(savedEvent);
        Result savedResult = resultRepository.save(result);
        // 빈 rollingpaper 생성
        RollingPaper rollingPaper = new RollingPaper(savedResult);
        rollingPaperRepository.save(rollingPaper);

        return savedEvent.getSessionId();
    }


    // 호스트의 세션 status closed로 변경
    @Override
    public void deleteSession(String sessionId, SessionReqDto sessionReqDto) {

        // 세션의 호스트Id와 지금 전달받은 userId가 맞는지 확인
        Event event = eventRepository.findBySessionId(sessionId);
        if (!event.getUser().getId().equals(sessionReqDto.getUserId())) {
            log.error("호스트Id가 아님"); //임시로 하는 거.
            throw new RestApiException(OpenviduErrorCode.NOT_HOST_USER_ID);
        }

        //session의 status를 CLOSED로 변경
        event.closeSession(sessionReqDto);

        //session 저장
        eventRepository.save(event);


    }

    // 롤링페이퍼 파일 받아서 유효성 검사
    @Override
    public Map<String, MultipartFile> createRollingpaperFileMap(RollingpaperReqDto rollingpaperReqDto) {

        Map<String, MultipartFile> fileMap = new HashMap<>();
        // 음성이 있는 경우 map에 저장
        MultipartFile voice = rollingpaperReqDto.getVoice();
        if (voice != null && !voice.isEmpty()) {
            fileMap.put("voice", voice);
        }
        // 영상이 있는 경우 map에 저장
        MultipartFile video = rollingpaperReqDto.getVideo();
        if (video != null && !video.isEmpty()) {
            fileMap.put("video", video);
        }

        return fileMap;
    }
}
