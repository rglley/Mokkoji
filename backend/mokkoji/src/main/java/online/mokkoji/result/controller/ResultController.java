package online.mokkoji.result.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.common.auth.jwt.util.JwtUtil;
import online.mokkoji.result.dto.request.RollingPaperReqDto;
import online.mokkoji.result.service.ResultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${api.version}/results")
public class ResultController {

    private final ResultService resultService;
    private final JwtUtil jwtUtil;

    @GetMapping("/lists")
    public ResponseEntity<Map<String, Object>> getResult(HttpServletRequest req) {
        log.info("행사 리스트 상세 요청");
        String provider = jwtUtil.getProvider(req);
        String email = jwtUtil.getEmail(req);

        Map<String, Object> result = resultService.getResultMap(provider, email);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    // 기억 편집화면
    @GetMapping("/{resultId}/memories")
    public ResponseEntity<Map<String, Object>> getRollingpaperAndPhotoEdit(@PathVariable Long resultId) {

        Map<String, Object> photoAndMessageMap = resultService.getPhotoAndMessageMap(resultId);

        return new ResponseEntity<>(photoAndMessageMap, HttpStatus.OK);
    }

    // 롤링페이퍼 편집 완료
    @PatchMapping("/{resultId}/memories/rollingpaper")
    public ResponseEntity<String> updateRollingpaper(@PathVariable Long resultId,
                                                     @RequestBody RollingPaperReqDto rollingPaperReqDto) {

        resultService.updateRollingpaper(resultId, rollingPaperReqDto);

        return new ResponseEntity<>("편집 완료", HttpStatus.OK);
    }
}
