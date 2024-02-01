package online.mokkoji.result.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.result.domain.Message;
import online.mokkoji.result.domain.Photo;
import online.mokkoji.result.domain.Result;
import online.mokkoji.result.dto.response.MemoryResDto;
import online.mokkoji.result.dto.response.RecollectionResDto;
import online.mokkoji.result.repository.MessageRepository;
import online.mokkoji.result.repository.PhotoRepository;
import online.mokkoji.result.repository.ResultRepository;
import online.mokkoji.user.domain.Provider;
import org.redisson.api.RMapCache;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;
    private final RMapCache<String, Photo> photoRMapCache;
    private final RMapCache<String, Message> messageRMapCache;
    private final PhotoRepository photoRepository;
    private final MessageRepository messageRepository;

    @Override
    public Map<String, Object> readResult(String provider, String email) {
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
                        .date(date)
                        .participantCount(result.getEvent().getParticipantCount())
                        .isPaperEdited(result.getRollingpaper().isEdited())
                        .isMosaicCreated(result.getPhotomosaic() != null)
                        .build();

                memoryList.add(memoryResDto);
                continue;
            }

            RecollectionResDto recollectionResDto = RecollectionResDto.builder()
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

    @Override
    public void createPhoto(Photo photo) {
        photoRMapCache.put(photo.getUrl(), photo);
    }

    @Override
    public void createMessage(Message message) {
        messageRMapCache.put(message.getWriter(), message);
    }

    @Override
    public void saveRemainingPhotos() {
        for (Map.Entry<String, Photo> entry : photoRMapCache.entrySet()) {
            photoRepository.save(entry.getValue());
            photoRMapCache.remove(entry);
        }

    }

    @Override
    public void saveRemainingMessages() {
        for (Map.Entry<String, Message> entry : messageRMapCache.entrySet()) {
            messageRepository.save(entry.getValue());
            photoRMapCache.remove(entry);

        }
    }


}
