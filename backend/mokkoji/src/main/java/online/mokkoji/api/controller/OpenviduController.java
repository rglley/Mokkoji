package online.mokkoji.api.controller;

import online.mokkoji.api.request.SessionReqDto;
import online.mokkoji.api.response.SessionObjectResDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class OpenviduController {


    //Session 생성 메서드
    @PostMapping("/openvidu/api/sessions")
    public ResponseEntity<?> addOpenviduSession(@RequestBody SessionReqDto session) {


        if (session == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            //sessionId ses_랜덤문자열 생성
            String sessionId = "ses_" + RandomStringUtils.randomAlphanumeric(10);
            //session object 리턴
            SessionObjectResDto sessionObject = new SessionObjectResDto(sessionId, session.getDefaultRecordingProperties());

            //JPA로 저장


            //Redis에 저장


            return new ResponseEntity<>(sessionObject, HttpStatus.OK);
        }
    }

}
