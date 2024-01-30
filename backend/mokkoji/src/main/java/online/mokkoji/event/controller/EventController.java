package online.mokkoji.event.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.S3.S3ServiceImpl;
import online.mokkoji.common.auth.jwt.util.JwtUtil;
import online.mokkoji.event.domain.Event;
import online.mokkoji.event.dto.request.RollingpaperReqDto;
import online.mokkoji.event.dto.response.PhotoResDto;
import online.mokkoji.event.dto.response.RollingpaperRedisDto;
import online.mokkoji.event.repository.EventRepository;
import online.mokkoji.event.service.EventService;
import online.mokkoji.user.domain.User;
import online.mokkoji.user.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;
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
    private final UserService userService;
    private final EventService eventService;
    private final S3ServiceImpl s3Service;
    private final JwtUtil jwtUtil;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    // 캡쳐사진 저장
    @PostMapping("/photos/{sessionId}")
    public ResponseEntity<String> addPhoto(@PathVariable("sessionId") String sessionId,
                                           HttpServletRequest req,
                                           MultipartFile photo) throws IOException {

        String provider = jwtUtil.getProvider(req);
        String email = jwtUtil.getEmail(req);

        User user = userService.getByProviderAndEmail(provider, email);

        Event event = eventRepository.findBySessionId(sessionId);
        Long resultId = event.getResult().getId();
        String url = s3Service.uploadImage(photo, user.getId(), resultId);

        log.info("url : {}", url);

        PhotoResDto photoToRedis = new PhotoResDto(user.getId(), resultId, url);
        redisTemplate.opsForList().leftPush("photos", objectMapper.writeValueAsString(photoToRedis));

        return new ResponseEntity<>("사진 업로드 완료", HttpStatus.OK);
    }

    //롤링페이퍼 저장
    @PostMapping("/rollingpapers/{sessionId}")
    public ResponseEntity<String> addRollingpaper(@PathVariable("sessionId") String sessionId,
                                                  HttpServletRequest req,
                                                  @RequestPart(value = "voice", required = false) MultipartFile voice,
                                                  @RequestPart(value = "video", required = false) MultipartFile video,
                                                  @RequestPart("text") RollingpaperReqDto rollingpaperReqDto) throws IOException {

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
        Map<String, String> urlMap = s3Service.uploadRollingpaper(fileMap, user.getId(), resultId);
        RollingpaperRedisDto redisDto = new RollingpaperRedisDto(user.getId(), resultId, rollingpaperReqDto.getText(), urlMap);
        redisTemplate.opsForList().leftPush("rollingpaper", objectMapper.writeValueAsString(redisDto));

        return new ResponseEntity<>("롤링페이퍼 업로드 완료", HttpStatus.OK);

    }

}
