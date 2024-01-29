package online.mokkoji.event.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.event.dto.request.PhotoReqDto;
import online.mokkoji.event.dto.request.RollingpaperReqDto;
import online.mokkoji.S3.S3ServiceImpl;
import online.mokkoji.event.domain.Event;
import online.mokkoji.event.repository.EventRepository;
import online.mokkoji.event.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    // 캡쳐사진 저장
    @PostMapping("/photos/{sessionId}")
    public ResponseEntity<Map<String, String>> addPhoto(@PathVariable("sessionId") String sessionId,
                                                        @RequestPart("photo") MultipartFile photo,
                                                        @RequestPart("userId") PhotoReqDto photoReqDto) throws IOException {

//        Event event = eventRepository.findBySessionId(sessionId);
//        Long resultId = event.getResult().getId();
        Long resultId = 1L;
        URL s3Url = s3Service.uploadImage(photo, photoReqDto.getUserId(), resultId);

        log.info(s3Url.toString());

        Map<String, String> response = new HashMap<>();
        response.put("message", "사진 업로드 완료");

        // TODO: 2024.01.28 redis에 url 저장

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // TODO : 2024.01.28 여기도 형식 바꿔야 함
    //롤링페이퍼 저장
    @PostMapping("/rollingpapers/{sessionId}")
    public ResponseEntity<Map<String, String>> addRollingpaper(@PathVariable("sessionId") String sessionId,
                                                               @RequestBody RollingpaperReqDto rollingpaperReqDto) throws IOException {

        Event event = eventRepository.findBySessionId(sessionId);
        Long resultId = event.getResult().getId();

        Map<String, MultipartFile> fileMap = new HashMap<>();

        String text = rollingpaperReqDto.getText();

        if (!text.isEmpty() && text.length() > 0) {
            // TODO : 2023.01.28 redis에 text 저장
        }

        // 음성이 있는 경우 map에 저장
        MultipartFile voice = rollingpaperReqDto.getVoice();
        // TODO : ispresent 사용, 서비스로 null처리 이동 다 dto에 담아서
        if (voice != null && !voice.isEmpty()) {
            fileMap.put("voice", voice);
        }
        // 영상이 있는 경우 map에 저장
        MultipartFile video = rollingpaperReqDto.getVideo();
        if (video != null && !video.isEmpty()) {
            fileMap.put("video", video);
        }

        // 미디어 파일이 있는 경우만 uploadRollingpaper 실행
        if (fileMap.size() > 0) {
            Map<String, URL> urlMap = s3Service.uploadRollingpaper(fileMap, rollingpaperReqDto.getUserId(), resultId);

            // TODO: 2024.01.28 redis에 저장
        }


        Map<String, String> response = new HashMap<>();
        response.put("message", "롤링페이퍼 업로드 완료");


        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
