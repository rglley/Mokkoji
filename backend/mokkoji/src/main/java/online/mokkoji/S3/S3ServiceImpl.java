package online.mokkoji.S3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.common.exception.RestApiException;
import online.mokkoji.common.exception.errorCode.EventErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class S3ServiceImpl implements S3Service {


    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3Client amazonS3Client;


    // 사진 업로드 후 레디스에 저장
    @Override
    public String uploadImage(MultipartFile multipartFile, Long userId, Long resultId) throws IOException {

        String dir = "photos";
        String subDir = "photoList";
        String prefix = "pic_";
        String fileName = createFileName(userId.toString(), resultId.toString(), dir, subDir, prefix, multipartFile.getOriginalFilename());

        upload(multipartFile, fileName);

        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    // 롤링페이퍼 업로드
    @Override
    public Map<String, String> uploadRollingpaper(Map<String, MultipartFile> multipartFiles, Long userId, Long resultId) throws IOException {

        if (multipartFiles == null) {
            log.info("파일 없음");
            return null;
        }

        String dir = "rollingpaper";
        String subDir, prefix;
        Map<String, String> urlMap = new HashMap<>();

        for (Map.Entry<String, MultipartFile> fileEntry : multipartFiles.entrySet()) {
            // 음성인 경우
            if (fileEntry.getKey().equals("voice")) {
                subDir = "voice";
                prefix = "voi_";
            } else {
                subDir = "video";
                prefix = "vid_";
            }

            MultipartFile multipartFile = fileEntry.getValue();

            // 사진_유저ID_결과물ID
            String fileName = createFileName(userId.toString(), resultId.toString(), dir, subDir, prefix, multipartFile.getOriginalFilename());

            upload(multipartFile, fileName);


            urlMap.put(fileEntry.getKey(), amazonS3Client.getUrl(bucket, fileName).toString());

        }
        return urlMap;
    }

    // S3에 upload
    private void upload(MultipartFile multipartFile, String fileName) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(multipartFile.getContentType());
        metadata.setContentLength(multipartFile.getSize());
        PutObjectRequest request = new PutObjectRequest(bucket, fileName, multipartFile.getInputStream(), metadata);

        try {
            amazonS3Client.putObject(request);
        } catch (AmazonServiceException e) {
            // TODO: 2024.01.28 에러 잡기 공부하고 수정ㄱㄱ
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }

    }

    // 파일 이름 생성
    private String createFileName(String userId, String resultId, String dir, String subDir, String prefix, String fileName) {
        return String.format("%s/%s/%s/%s/%s%s%s",
                userId, resultId, dir, subDir, prefix, UUID.randomUUID(), getFileExtension(fileName));
    }

    // 확장자 유무 확인
    private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new RestApiException(EventErrorCode.NO_FILE_EXTENSION);
        }
    }
}
