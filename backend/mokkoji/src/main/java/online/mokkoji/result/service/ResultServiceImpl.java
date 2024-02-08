package online.mokkoji.result.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.common.exception.RestApiException;
import online.mokkoji.common.exception.errorCode.ResultErrorCode;
import online.mokkoji.event.dto.response.PhotoResDto;
import online.mokkoji.result.domain.Photo;
import online.mokkoji.result.domain.Result;
import online.mokkoji.result.domain.RollingPaper.BackgroundTemplate;
import online.mokkoji.result.domain.RollingPaper.Message;
import online.mokkoji.result.domain.RollingPaper.PostitTemplate;
import online.mokkoji.result.domain.RollingPaper.RollingPaper;
import online.mokkoji.result.dto.request.RollingPaperReqDto;
import online.mokkoji.result.dto.response.MemoryResDto;
import online.mokkoji.result.dto.response.MessageResDto;
import online.mokkoji.result.dto.response.RecollectionResDto;
import online.mokkoji.result.dto.response.RollingpaperResDto;
import online.mokkoji.result.repository.*;
import online.mokkoji.user.domain.Provider;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;
    private final PhotoRepository photoRepository;
    private final MessageRepository messageRepository;
    private final BackgroundTemplateRepository backgroundTemplateRepository;
    private final PostitTemplateRepository postitTemplateRepository;
    private final RollingPaperRepository rollingPaperRepository;

    // 사진과 메시지 리스트로 담는 메서드
    @Override
    public Map<String, Object> getResultMap(String provider, String email) {
        List<Result> resultList = resultRepository.findAllByUser_ProviderAndUser_EmailOrderByIdDesc(Provider.valueOf(provider), email);

        if (resultList.isEmpty())
            return null;

        Map<String, Object> resultMap = new HashMap<>();
        List<MemoryResDto> memoryList = new ArrayList<>();
        List<RecollectionResDto> recollectionList = new ArrayList<>();

        for (Result result : resultList) {
            LocalDate date = result.getEvent().getStartTime().toLocalDate();

            if (result.getStatus().getKey().equals("memory")) {
                MemoryResDto memoryResDto = MemoryResDto.builder()
                        .id(result.getId())
                        .date(date)
                        .participantCount(result.getEvent().getParticipantCount())
                        .isPaperEdited(result.getRollingpaper().isEdited())
                        .isMosaicCreated(result.getPhotomosaic() != null)
                        .build();

                memoryList.add(memoryResDto);
                continue;
            }

            RecollectionResDto recollectionResDto = RecollectionResDto.builder()
                    .id(result.getId())
                    .date(date)
                    .image(result.getImage())
                    .name(result.getName())
                    .content(result.getContent())
                    .build();

            recollectionList.add(recollectionResDto);
        }

        resultMap.put("memoryList", memoryList);
        resultMap.put("recollectionList", recollectionList);
        return resultMap;
    }

    // 사진 저장
    @Override
    public void createPhoto(PhotoResDto photoResDto) {
        Photo photo = Photo.builder().resultId(photoResDto.getResultId()).photoPath(photoResDto.getPhotoPath()).build();
        photoRepository.save(photo);
    }

    // 메시지 저장
    @Override
    public void createMessage(MessageResDto messageResDto) {
        Message message = Message.builder().paperId(messageResDto.getPaperId())
                .writer(messageResDto.getWriter())
                .text(messageResDto.getText())
                .voicePath(messageResDto.getVoicePath())
                .videoPath(messageResDto.getVideoPath())
                .build();
        messageRepository.save(message);
    }


    // 기억 편집 화면에서 필요한 사진과 메시지 불러오는 메서드
    @Override
    @Cacheable(cacheNames = "photoList", key = "#resultId", cacheManager = "cacheManager")
    public Map<String, Object> getPhotoAndMessageMap(Long resultId) {
        Map<String, Object> resultMap = new HashMap<>();

        // result를 통해 롤링페이퍼 가져옴
        Result result = getResult(resultId);
        RollingPaper rollingPaper = result.getRollingpaper();

        // 롤링페이퍼 설정된 템플릿 가져옴
        RollingpaperResDto rollingpaperDto = new RollingpaperResDto();
        rollingpaperDto.setBackgroundTemplate(rollingPaper.getBackgroundTemplate());
        rollingpaperDto.setPostitTemplate(rollingPaper.getPostitTemplate());

        // 사진 루트 가져옴
        List<Photo> photoList = photoRepository.findAllByResultId(resultId);
        List<String> photoPathList = new LinkedList<>();
        for (Photo photo : photoList) {
            photoPathList.add(photo.getPhotoPath());
        }

        resultMap.put("rollingpaperTemplate", rollingpaperDto);
        resultMap.put("photoPathList", photoPathList);

        return resultMap;

    }

    // 롤링페이퍼 템플릿 변경
    @Override
    public void updateRollingpaper(Long resultId, RollingPaperReqDto rollingPaperReqDto) {
        Result result = getResult(resultId);
        RollingPaper rollingpaper = result.getRollingpaper();

        // 요철 들어온 이름에 맞는 템플릿 찾기
        Map<String, Integer> backgroundNameToId = Map.of(
                "basic", 1,
                "wedding", 2,
                "school", 3,
                "lunar", 4,
                "baby", 5
        );

        Map<String, Integer> postitNameToId = Map.of(
                "rainbow", 1,
                "green", 2,
                "blue", 3,
                "pink", 4,
                "yellow", 5
        );


        // id에 맞는 템플릿 선택 후 추가
        String backgroundName = rollingPaperReqDto.getBackgroundName();
        int bgId = backgroundNameToId.getOrDefault(backgroundName, rollingpaper.getBackgroundTemplate().getId());

        BackgroundTemplate backgroundTemplate = backgroundTemplateRepository.findById(bgId)
                .orElseThrow(() -> new RestApiException(ResultErrorCode.BACKGROUND_NOT_FOUND));

        String postitName = rollingPaperReqDto.getPostitName();
        int postitId = postitNameToId.getOrDefault(postitName, rollingpaper.getPostitTemplate().getId());

        PostitTemplate postitTemplate = postitTemplateRepository.findById(postitId)
                .orElseThrow(() -> new RestApiException(ResultErrorCode.POSTIT_NOT_FOUND));

        rollingpaper.setBackgroundTemplate(backgroundTemplate);
        rollingpaper.setPostitTemplate(postitTemplate);

        // 변경내용 수정
        rollingPaperRepository.save(rollingpaper);
    }

    // 결과객체 가져오는 메서드
    private Result getResult(Long resultId) {
        return resultRepository.findById(resultId)
                .orElseThrow(() -> new RestApiException(ResultErrorCode.RESULT_NOT_FOUND));
    }


}
