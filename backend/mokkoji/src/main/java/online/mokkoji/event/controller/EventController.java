package online.mokkoji.event.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.S3.S3ServiceImpl;
import online.mokkoji.common.auth.jwt.util.JwtUtil;
import online.mokkoji.event.domain.Event;
import online.mokkoji.event.dto.request.RollingpaperReqDto;
import online.mokkoji.event.repository.EventRepository;
import online.mokkoji.event.service.EventService;
import online.mokkoji.result.domain.Message;
import online.mokkoji.result.domain.Photo;
import online.mokkoji.result.service.ResultService;
import online.mokkoji.user.domain.User;
import online.mokkoji.user.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventRepository eventRepository;
    private final UserServiceImpl userServiceImpl;
    private final EventService eventService;
    private final S3ServiceImpl s3Service;
    private final JwtUtil jwtUtil;
    private final ResultService resultService;

    // 캡쳐사진 저장
    @PostMapping("/photos/{sessionId}")
    public ResponseEntity<String> addPhoto(@PathVariable("sessionId") String sessionId,
                                           HttpServletRequest req,
                                           MultipartFile photo) throws IOException {

        String provider = jwtUtil.getProvider(req);
        String email = jwtUtil.getEmail(req);

        User user = userServiceImpl.getByProviderAndEmail(provider, email);

        Event event = eventRepository.findBySessionId(sessionId);
        Long resultId = event.getResult().getId();
//        Long resultId = 2L;
        String url = s3Service.uploadImage(photo, user.getId(), resultId);
//        String url = s3Service.uploadImage(photo, 1L, resultId);

        log.info("url : {}", url);

        Photo photoToRedis = new Photo(resultId, url);
        log.info("photo : {}", photoToRedis.toString());
        resultService.createPhoto(photoToRedis);

        return new ResponseEntity<>("사진 저장 완료", HttpStatus.OK);
    }

    //롤링페이퍼 저장
    @PostMapping("/rollingpapers/{sessionId}")
    public ResponseEntity<String> addRollingpaper(@PathVariable("sessionId") String sessionId,
                                                  HttpServletRequest req,
                                                  @RequestPart(value = "voice", required = false) MultipartFile voice,
                                                  @RequestPart(value = "video", required = false) MultipartFile video,
                                                  @RequestPart("writerAndText") RollingpaperReqDto rollingpaperReqDto) throws IOException {

        String provider = jwtUtil.getProvider(req);
        String email = jwtUtil.getEmail(req);

        User user = userServiceImpl.getByProviderAndEmail(provider, email);

        Event event = eventRepository.findBySessionId(sessionId);
        Long paperId = event.getResult().getRollingpaper().getId();
//        Long paperId = 1L;

        rollingpaperReqDto.setVoice(voice);
        rollingpaperReqDto.setVideo(video);

        // 파일들 유효성 검사 후 map에 담음
        Map<String, MultipartFile> fileMap = eventService.createRollingpaperFileMap(rollingpaperReqDto);


        // 유효성 검사 후 파일 S3에 업로드
        Map<String, String> urlMap = s3Service.uploadRollingpaper(fileMap, user.getId(), paperId);
//        Map<String, String> urlMap = s3Service.uploadRollingpaper(fileMap, 2L, paperId);
        Message message = new Message(paperId, rollingpaperReqDto.getWriter(), rollingpaperReqDto.getText(), urlMap);
        resultService.createMessage(message);
        return new ResponseEntity<>("롤링페이퍼 업로드 완료", HttpStatus.OK);

    }


}
