package online.mokkoji.api.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public interface S3Service {

    // 사진 업로드
    URL uploadImage(MultipartFile multipartFile, Long userId, Long resultId) throws IOException;

    // 롤링페이퍼 업로드
    Map<String, URL> uploadRollingpaper(Map<String, MultipartFile> multipartFiles, Long userId, Long resultId) throws IOException;

}
