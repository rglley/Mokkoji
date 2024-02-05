package online.mokkoji.result.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.common.exception.RestApiException;
import online.mokkoji.common.exception.errorCode.ResultErrorCode;
import online.mokkoji.event.dto.response.PhotoResDto;
import online.mokkoji.result.domain.Photo;
import online.mokkoji.result.domain.Photomosaic;
import online.mokkoji.result.domain.Result;
import online.mokkoji.result.domain.RollingPaper.*;
import online.mokkoji.result.dto.request.RollingPaperReqDto;
import online.mokkoji.result.dto.response.*;
import online.mokkoji.result.repository.*;
import online.mokkoji.user.domain.Provider;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final RollingPaperRepository rollingPaperRepository;
    private final BackgroundTemplateRepository backgroundTemplateRepository;
    private final PostitTemplateRepository postitTemplateRepository;

    // 사진과 메시지 리스트로 담는 메서드
    @Override
    public Map<String, Object> getResultList(String provider, String email) {
        List<Result> resultList = resultRepository.findAllByUser_ProviderAndUser_EmailOrderByIdDesc(Provider.valueOf(provider), email);

        if (resultList.isEmpty())
            return null;

        Map<String, Object> resultMap = new HashMap<>();
        List<MemoryInfoResDto> memoryList = new ArrayList<>();
        List<RecollectionInfoResDto> recollectionList = new ArrayList<>();

        for (Result result : resultList) {
            LocalDate date = result.getEvent().getStartTime().toLocalDate();

            if (result.getStatus().getKey().equals("memory")) {
                MemoryInfoResDto memoryInfoResDto = MemoryInfoResDto.builder()
                        .resultId(result.getId())
                        .date(date)
                        .participantCount(result.getEvent().getParticipantCount())
                        .isPaperEdited(result.getRollingpaper().isEdited())
                        .isMosaicCreated(result.getPhotomosaic() != null)
                        .build();

                memoryList.add(memoryInfoResDto);
                continue;
            }

            RecollectionInfoResDto recollectionInfoResDto = RecollectionInfoResDto.builder()
                    .resultId(result.getId())
                    .date(date)
                    .image(result.getImage())
                    .name(result.getName())
                    .content(result.getContent())
                    .build();

            recollectionList.add(recollectionInfoResDto);
        }

        resultMap.put("memoryList", memoryList);
        resultMap.put("recollectionList", recollectionList);
        return resultMap;
    }

    @Override
    public ResultResDto getResult(Long resultId, Pageable pageable) {
        Optional<Result> findResult = resultRepository.findById(resultId);

        if(findResult.isEmpty())
            throw new RestApiException(ResultErrorCode.RESULT_NOT_FOUND);

        Result result = findResult.get();

        RollingPaper rollingPaper = result.getRollingpaper();

        if(rollingPaper == null)
            throw new RestApiException(ResultErrorCode.ROLLINGPAPER_NOT_FOUND);

        Page<Message> messageList = messageRepository.findAllByRollingPaper_Id(resultId, pageable);

        Photomosaic photomosaic = result.getPhotomosaic();

        return ResultResDto.builder()
                .backgroundTemplate(rollingPaper.getBackgroundTemplate().getPath())
                .postitTemplate(rollingPaper.getPostitTemplate().getPath())
                .messageList(messageList)
                .photomosaic(photomosaic == null ? "" : photomosaic.getPath())
                .build();
    }

    @Override
    public void createRecollection(Long resultId) {
        Optional<Result> findResult = resultRepository.findById(resultId);

        if(findResult.isEmpty())
            throw new RestApiException(ResultErrorCode.RESULT_NOT_FOUND);

        Result result = findResult.get();

        if(result.getStatus().getKey().equals("recollection"))
            throw new RestApiException(ResultErrorCode.ALREADY_RECOLLECTION);

        result.updateStatus();
        resultRepository.save(result);
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
        Long paperId = messageResDto.getPaperId();

        RollingPaper rollingPaper = rollingPaperRepository.getReferenceById(paperId);

        Message message = Message.builder()
                .rollingPaper(rollingPaper)
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
        Result result = getResultById(resultId);
        RollingPaper rollingPaper = result.getRollingpaper();

        // 롤링페이퍼 설정된 템플릿 가져옴
        RollingpaperEditResDto rollingpaperDto = new RollingpaperEditResDto();
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
        Result result = getResultById(resultId);
        RollingPaper rollingpaper = result.getRollingpaper();

        // 요철 들어온 이름에 맞는 템플릿 찾기
        BackgroundName backgroundName = BackgroundName.valueOf(rollingPaperReqDto.getBackgroundName().toUpperCase());
        BackgroundTemplate backgroundTemplate = backgroundTemplateRepository.findByBackgroundName(backgroundName)
                .orElseThrow(()->new RestApiException(ResultErrorCode.BACKGROUND_NOT_FOUND));

        PostitName postitName = PostitName.valueOf(rollingPaperReqDto.getPostitName().toUpperCase());
        PostitTemplate postitTemplate = postitTemplateRepository.findByPostitName(postitName)
                .orElseThrow(() -> new RestApiException(ResultErrorCode.POSTIT_NOT_FOUND));



        rollingpaper.setBackgroundTemplate(backgroundTemplate);
        rollingpaper.setPostitTemplate(postitTemplate);

        // 변경내용 수정
        rollingPaperRepository.save(rollingpaper);
    }

    // 결과객체 가져오는 메서드
    private Result getResultById(Long resultId) {
        return resultRepository.findById(resultId)
                .orElseThrow(() -> new RestApiException(ResultErrorCode.RESULT_NOT_FOUND));
    }


}
