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
import online.mokkoji.event.dto.response.PhotoResDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class S3ServiceImpl implements S3Service {


    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3Client amazonS3Client;


    // 사진 한 장 업로드 후 url 리턴
    @Override
    public PhotoResDto uploadOnePhoto(MultipartFile multipartFile, Long userId, Long resultId) throws IOException {

        return getPhotoResDto(multipartFile, userId, resultId);
    }


    // 사진 여러장 업로드
    @Override
    public List<PhotoResDto> uploadPhotoList(List<MultipartFile> photoList, Long userId, Long resultId) {

        List<PhotoResDto> dtoList = new ArrayList<>();

        for (MultipartFile photo : photoList) {
            try {
                PhotoResDto photoResDto = getPhotoResDto(photo, userId, resultId);
                dtoList.add(photoResDto);
            } catch (IOException e) {
                // TODO : 이부분도 RestApiException으로 해야하는지?
                throw new RuntimeException(e);
            }
        }
        return dtoList;
    }


    // 롤링페이퍼 업로드
    @Override
    public Map<String, String> uploadRollingpaper(Map<String, MultipartFile> multipartFiles, Long userId, Long paperId) throws IOException {

        if (multipartFiles == null) {
            log.info("파일 없음");
            return null;
        }

        String dir = "rollingpaper";
        String subDir = "";
        String prefix;
        Map<String, String> urlMap = new HashMap<>();

        for (Map.Entry<String, MultipartFile> fileEntry : multipartFiles.entrySet()) {
            // 음성인 경우
            if (fileEntry.getKey().equals("voice")) {
                prefix = "voi_";
            } else {
                prefix = "vid_";
            }

            MultipartFile multipartFile = fileEntry.getValue();

            // 사진_유저ID_결과물ID
            String fileName = createFileName(userId.toString(), paperId.toString(), dir, subDir, prefix, multipartFile.getOriginalFilename());

            upload(multipartFile, fileName);


            urlMap.put(fileEntry.getKey(), amazonS3Client.getUrl(bucket, fileName).toString());

        }
        return urlMap;
    }


    // 사진 하나 업로드 후 dto로 담음
    private PhotoResDto getPhotoResDto(MultipartFile multipartFile, Long userId, Long resultId) throws IOException {
        String dir = "photos";
        String subDir = "photoList";
        String prefix = "pic_";
        String fileName = createFileName(userId.toString(), resultId.toString(), dir, subDir, prefix, multipartFile.getOriginalFilename());

        upload(multipartFile, fileName);

        return new PhotoResDto(resultId, amazonS3Client.getUrl(bucket, fileName).toString());
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
        if (subDir.isBlank()) return String.format("%s/%s/%s/%s%s%s",
                userId, resultId, dir, prefix, UUID.randomUUID(), getFileExtension(fileName));
        return String.format("%s/%s/%s/%s/%s%s%s",
                userId, resultId, dir, subDir, prefix, UUID.randomUUID(), getFileExtension(fileName));
    }

    // 확장자 유무 확인
    private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new RestApiException(EventErrorCode.FILE_EXTENSION_NOT_FOUND);
        }
    }
}
