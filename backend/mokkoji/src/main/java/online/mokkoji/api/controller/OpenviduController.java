package online.mokkoji.api.controller;

import io.openvidu.java.client.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.api.request.SessionReqDto;
import online.mokkoji.api.service.EventService;
import online.mokkoji.db.repository.EventRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class OpenviduController {

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
    public ResponseEntity<Map<String, String>> addSession(@RequestBody(required = false) Map<String, Object> params)
            throws OpenViduJavaClientException, OpenViduHttpException {

        // request body 객체로 직렬화
        SessionProperties properties = SessionProperties.fromJson(params).build();

        //세션 생성
        Session session = openvidu.createSession(properties);


        // DB에 담을 유저Id를 받기
        Long userId = eventService.getUserId(params);


        // DB에 저장할 Dto 생성
        SessionReqDto sessionReqDto = new SessionReqDto(userId, session.getSessionId(), session.createdAt());

        // DB에 저장
        String sessionDBId = eventService.createSession(sessionReqDto);

        // 리턴값(sessionId) 담는 map 생성
        Map<String, String> response = new HashMap<>();

        // map에 담기
        response.put("sessionId", session.getSessionId());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // Session 삭제
    @DeleteMapping("/api/sessions/{sessionId}")
    public ResponseEntity<?> deleteSession(@PathVariable("sessionId") String sessionId,
                                           @RequestBody(required = false) SessionReqDto sessionReqDto)
            throws OpenViduJavaClientException, OpenViduHttpException {

        Session activeSession = openvidu.getActiveSession(sessionId);
        eventService.deleteSession(sessionId, sessionReqDto);

        activeSession.close();

        // redis에서 롤링페이퍼, 사진들 정보 받아옴

        // 결과물 파일 저장(resultService)


        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // Session에 Connect
    @PostMapping("/api/sessions/{sessionId}/connections")
    public ResponseEntity<Map<String, String>> addConnection(@PathVariable("sessionId") String sessionId,
                                                             @RequestBody(required = false) Map<String, Object> params)
            throws OpenViduJavaClientException, OpenViduHttpException {
        Session session = openvidu.getActiveSession(sessionId);

        ConnectionProperties properties = ConnectionProperties.fromJson(params).build();
        Connection connection = session.createConnection(properties);

        // return값 담기
        Map<String, String> response = new HashMap<>();
        response.put("connectionToken", connection.getToken());
        response.put("Status", "Session에 참여자 연결 성공");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
