package online.mokkoji.event.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.S3.S3ServiceImpl;
import online.mokkoji.event.dto.request.PhotoReqDto;
import online.mokkoji.event.dto.request.RollingpaperReqDto;
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
    public ResponseEntity<?> addPhoto(@PathVariable("sessionId") String sessionId,
                                      @RequestPart("photo") MultipartFile photo,
                                      @RequestPart("userId") PhotoReqDto photoReqDto) throws IOException {

        // TODO : 2024.01.29 jwt에서 사용자 권한 받아오기
//        Event event = eventRepository.findBySessionId(sessionId);
//        Long resultId = event.getResult().getId();
        Long resultId = 1L;
        URL s3Url = s3Service.uploadImage(photo, photoReqDto.getUserId(), resultId);

        log.info(s3Url.toString());

        // TODO: 2024.01.28 redis에 url 저장

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //롤링페이퍼 저장
    @PostMapping("/rollingpapers/{sessionId}")
    public ResponseEntity<Map<String, ?>> addRollingpaper(@PathVariable("sessionId") String sessionId,
                                                          @RequestPart(value = "voice", required = false) MultipartFile voice,
                                                          @RequestPart(value = "video", required = false) MultipartFile video,
                                                          @RequestPart("dto") RollingpaperReqDto rollingpaperReqDto) throws IOException {

        // TODO : 2024.01.29 jwt에서 사용자 정보 받기
//        Event event = eventRepository.findBySessionId(sessionId);
//        Long resultId = event.getResult().getId();
        Long resultId = 1L;

        rollingpaperReqDto.setVoice(voice);
        rollingpaperReqDto.setVideo(video);

        // 텍스트, 음성, 영상 유효성 검사 후 file, text 맵에 담음
        Map<String, Map> rollingpaperMap = eventService.createRollingpaperFileMap(rollingpaperReqDto);


        Map<String, MultipartFile> fileMap = rollingpaperMap.get("files");
        // 유효성 검사 후 파일 S3에 업로드
        Map<String, URL> urlMap = s3Service.uploadRollingpaper(fileMap, rollingpaperReqDto.getUserId(), resultId);

        Map<String, Map> response = new HashMap<>();
        response.put("text", rollingpaperMap.get("text"));
        response.put("fileURL", urlMap);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }


}
