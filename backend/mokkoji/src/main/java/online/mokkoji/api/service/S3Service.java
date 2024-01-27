package online.mokkoji.api.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

public interface S3Service {

    // 사진 업로드
    URL createImage(MultipartFile multipartFile, Long userId, Long resultId) throws IOException;


}
