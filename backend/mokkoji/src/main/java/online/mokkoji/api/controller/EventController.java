package online.mokkoji.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.api.request.PhotoReqDto;
import online.mokkoji.api.service.EventService;
import online.mokkoji.api.service.S3ServiceImpl;
import online.mokkoji.db.entity.Event.Event;
import online.mokkoji.db.repository.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventRepository eventRepository;
    private final EventService eventService;
    private final S3ServiceImpl s3Service;

    // 캡쳐사진 전송받음
    @PostMapping("/photos/{sessionId}")
    public ResponseEntity<Map<String, String>> addPhoto(@PathVariable("sessionId") String sessionId,
                                                        @RequestBody PhotoReqDto photoReqDto) throws IOException {

        Event event = eventRepository.findBySessionId(sessionId);
        Long resultId = event.getResult().getId();
        URL s3Url = s3Service.createImage(photoReqDto.getPhoto(), photoReqDto.getUserId(), resultId);

        Map<String, String> response = new HashMap<>();
        response.put("Status", "사진 업로드 완료");

        // redis에 url 저장

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
