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
import online.mokkoji.user.domain.User;
import online.mokkoji.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventRepository eventRepository;
    private final UserService userService;
    private final EventService eventService;
    private final S3ServiceImpl s3Service;
    private final JwtUtil jwtUtil;

    // 캡쳐사진 저장
    @PostMapping("/photos/{sessionId}")
    public ResponseEntity<String> addPhoto(@PathVariable("sessionId") String sessionId,
                                           HttpServletRequest req,
                                           @RequestPart("photo") MultipartFile photo) throws IOException {

        String provider = jwtUtil.getProvider(req);
        String email = jwtUtil.getEmail(req);

        User user = userService.getByProviderAndEmail(provider, email);

        Event event = eventRepository.findBySessionId(sessionId);
        Long resultId = event.getResult().getId();
        URL s3Url = s3Service.uploadImage(photo, user.getId(), resultId);

        log.info(s3Url.toString());

        // TODO: 2024.01.28 redis에 url 저장

        return new ResponseEntity<>("사진 업로드 완료", HttpStatus.OK);
    }

    //롤링페이퍼 저장
    @PostMapping("/rollingpapers/{sessionId}")
    public ResponseEntity<String> addRollingpaper(@PathVariable("sessionId") String sessionId,
                                                  HttpServletRequest req,
                                                  @RequestPart(value = "voice", required = false) MultipartFile voice,
                                                  @RequestPart(value = "video", required = false) MultipartFile video,
                                                  @RequestPart("dto") RollingpaperReqDto rollingpaperReqDto) throws IOException {

        String provider = jwtUtil.getProvider(req);
        String email = jwtUtil.getEmail(req);

        User user = userService.getByProviderAndEmail(provider, email);
        Event event = eventRepository.findBySessionId(sessionId);
        Long resultId = event.getResult().getId();

        rollingpaperReqDto.setVoice(voice);
        rollingpaperReqDto.setVideo(video);

        // 텍스트, 음성, 영상 유효성 검사 후 file, text 맵에 담음
        Map<String, MultipartFile> fileMap = eventService.createRollingpaperFileMap(rollingpaperReqDto);


        // 유효성 검사 후 파일 S3에 업로드
        Map<String, URL> urlMap = s3Service.uploadRollingpaper(fileMap, user.getId(), resultId);

        // TODO : redis에 전부 저장

        return new ResponseEntity<>("롤링페이퍼 업로드 완료", HttpStatus.OK);

    }


}
