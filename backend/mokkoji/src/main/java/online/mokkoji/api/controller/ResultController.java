package online.mokkoji.api.controller;

import lombok.RequiredArgsConstructor;
import online.mokkoji.api.S3Uploader;
import online.mokkoji.api.request.UserReqDto;
import online.mokkoji.api.service.ResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/results")
public class ResultController {

    private final ResultService resultService;
    private final S3Uploader s3Uploader;

    @GetMapping
    public ResponseEntity<?> getResults(@RequestBody UserReqDto userReqDto) {
        String username = userReqDto.getUsername();
        return ResponseEntity.ok(resultService.getResults(username));
    }

    @GetMapping("/memory")
    public ResponseEntity<?> getRollingpaperTemplate(@RequestBody UserReqDto userReqDto) {
        String username = userReqDto.getUsername();
        return ResponseEntity.ok(resultService.getRollingpaper(username));
    }

    @PatchMapping("/memory/rollingpaper")
    public ResponseEntity<?> editRollingpaperTemplate(@RequestBody UserReqDto userReqDto) {
        return null;
    }

    @PostMapping("/memory/photomosaic")
    public ResponseEntity<?> addPhotomosaic(@RequestBody UserReqDto userReqDto) {
        return null;
    }

    @PatchMapping("/memory/photomosaic")
    public ResponseEntity<?> editPhotomosaic(@RequestBody UserReqDto userReqDto) {
        return null;
    }

    @PostMapping("/recollection")
    public ResponseEntity<?> addRecollection(@RequestBody UserReqDto userReqDto) {
        return null;
    }

    @GetMapping("/recollection")
    public ResponseEntity<?> getRecollection(@RequestBody UserReqDto userReqDto) {
        return null;
    }

    @GetMapping("/recollection/rollingpaper/{rollingpaperId}")
    public ResponseEntity<?> downloadRollingpaper(@RequestBody UserReqDto userReqDto) {
        return null;
    }

    @GetMapping("/recollection/image/{imageId}")
    public ResponseEntity<?> downloadImage(@RequestBody UserReqDto userReqDto) {
        return null;
    }

    @GetMapping("/recollection/photomosaic/{photomosaic}")
    public ResponseEntity<?> downloadPhotomosaic(@RequestBody UserReqDto userReqDto) {
        return null;
    }

    @PostMapping("/upload")
    public void create(MultipartFile multipartFile) {
        String fileName = "";
        if(multipartFile != null) { // 파일 업로드한 경우에만
            try {   // 파일 업로드
                fileName = s3Uploader.upload(multipartFile,"images");
                System.out.println("fileName = " + fileName);
            } catch (IOException e){
                return;
            }
        }
    }
}
