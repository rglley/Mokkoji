package online.mokkoji.api.controller;

import io.openvidu.java.client.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import online.mokkoji.api.request.SessionReqDto;
import online.mokkoji.api.service.EventService;
import online.mokkoji.db.entity.Event.Event;
import online.mokkoji.db.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class OpenviduController {

    private static final Logger log = LoggerFactory.getLogger(OpenviduController.class);

    private final EventService eventService;
    private final EventRepository eventRepository;


    @Value("${OPENVIDU_URL}")
    private String OPENVIDU_URL;

    @Value("${OPENVIDU_SECRET}")
    private String OPENVIDU_SECRET;

    private OpenVidu openvidu;

    @PostConstruct
    public void init() {
        this.openvidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
    }

    // Session 생성
    @PostMapping("/api/sessions")
    public ResponseEntity<String> addSession(@RequestBody(required = false) Map<String, Object> params)
            throws OpenViduJavaClientException, OpenViduHttpException {


        SessionProperties properties = SessionProperties.fromJson(params).build();
        Session session = openvidu.createSession(properties);


        // 유저Id를 받기
        Long userId;

        if (!params.containsKey("userId")) {
//            throw new IllegalArgumentException("로그인을 해주세요");
            log.error("유저 아이디 없음");
        }
//        userId = (Long) params.get("userId");
        userId = 1L; //임시


        SessionReqDto sessionReqDto = new SessionReqDto(userId, session.getSessionId(), session.createdAt());
        // DB에 저장
        String sessionDBId = eventService.createSession(sessionReqDto);


        // redis에 저장

        return new ResponseEntity<>(session.getSessionId(), HttpStatus.OK);
    }


    // Session 삭제
    @DeleteMapping("/api/sessions/{sessionId}")
    public ResponseEntity<?> deleteSession(@PathVariable("sessionId") String sessionId,
                                           @RequestBody(required = false) SessionReqDto sessionReqDto)
            throws OpenViduJavaClientException, OpenViduHttpException {

        Session activeSession = openvidu.getActiveSession(sessionId);

        if (activeSession == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // 세션의 호스트Id와 지금 전달받은 userId가 맞는지 확인
        Event event = eventRepository.findBySessionId(sessionId);
        if (event.getUser().getId() != sessionReqDto.getUserId()) {
            log.error("권한이 없습니다"); //임시로 하는 거.
            // throw 해야함
        }

        // 참가자 수 , 상태, 종료 시간 넣어야 함
        SessionReqDto sessionDto = new SessionReqDto(sessionId, sessionReqDto.getParticipantCount(), sessionReqDto.getEndTime());

        eventService.deleteSession(sessionDto);

        activeSession.close();

        if (!(openvidu.getActiveSession(sessionId) == null)) {
            log.error("세션 삭제가 되지 않았음"); // ㅈ
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // Session에 Connect
    @PostMapping("/api/sessions/{sessionId}/connections")
    public ResponseEntity<String> addConnection(@PathVariable("sessionId") String sessionId,
                                                @RequestBody(required = false) Map<String, Object> params)
            throws OpenViduJavaClientException, OpenViduHttpException {
        Session session = openvidu.getActiveSession(sessionId);
        if (session == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ConnectionProperties properties = ConnectionProperties.fromJson(params).build();
        Connection connection = session.createConnection(properties);

        return new ResponseEntity<>(connection.getToken(), HttpStatus.OK);
    }


}
