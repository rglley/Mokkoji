package online.mokkoji.api.service;

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
import java.net.URL;
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
    public URL uploadImage(MultipartFile multipartFile, Long userId, Long resultId) throws IOException {

        // S3 내의 사진용 폴더에 저장
        String dirName = "photos";
        // 사진_유저ID_결과물ID
        String prefix = "pic_" + userId + "_" + resultId + "_";
        String fileName = dirName + "/" + createFileName(multipartFile.getOriginalFilename(), prefix);

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

        return amazonS3Client.getUrl(bucket, fileName);
    }

    // 롤링페이퍼 업로드
    @Override
    public Map<String, URL> uploadRollingpaper(Map<String, MultipartFile> multipartFiles, Long userId, Long resultId) throws IOException {

        String dirName;
        String prefix;
        Map<String, URL> urlMap = new HashMap<>();

        for (Map.Entry<String, MultipartFile> fileEntry : multipartFiles.entrySet()) {
            // 음성인 경우
            if (fileEntry.getKey().equals("voice")) {
                dirName = "voices";
                prefix = "voi_" + userId + "_" + resultId + "_";
            } else {
                dirName = "videos";
                prefix = "vid_" + userId + "_" + resultId + "_";
            }

            MultipartFile multipartFile = fileEntry.getValue();

            // 사진_유저ID_결과물ID
            String fileName = dirName + "/" + createFileName(multipartFile.getOriginalFilename(), prefix);

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


            urlMap.put(fileEntry.getKey(), amazonS3Client.getUrl(bucket, fileName));

        }
        return urlMap;
    }


    // 파일 이름 생성
    private String createFileName(String fileName, String prefix) {
        return prefix.concat(UUID.randomUUID().toString()).concat(getFileExtension(fileName));
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
