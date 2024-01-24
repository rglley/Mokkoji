package online.mokkoji.api.controller;

import io.openvidu.java.client.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import online.mokkoji.api.request.SessionDto;
import online.mokkoji.api.service.EventService;
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


    @Value("${OPENVIDU_URL}")
    private String OPENVIDU_URL;

    @Value("${OPENVIDU_SECRET}")
    private String OPENVIDU_SECRET;

    private OpenVidu openvidu;

    @PostConstruct
    public void init() {
        this.openvidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
    }

    /**
     * Session 생성
     *
     * @param params The Session properties
     * @return The Session ID
     */
    @PostMapping("/api/sessions")
    public ResponseEntity<String> initializeSession(@RequestBody(required = false) Map<String, Object> params)
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


        SessionDto sessionDto = new SessionDto(userId, session.getSessionId(), session.createdAt());
        // DB에 저장
        String sessionDBId = eventService.createSession(sessionDto);


        // redis에 저장

        return new ResponseEntity<>(session.getSessionId(), HttpStatus.OK);
    }


    /**
     * 세션으로 연결
     *
     * @param sessionId The Session in which to create the Connection
     * @param params    The Connection properties
     * @return The Token associated to the Connection
     */
    @PostMapping("/api/sessions/{sessionId}/connections")
    public ResponseEntity<String> createConnection(@PathVariable("sessionId") String sessionId,
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
