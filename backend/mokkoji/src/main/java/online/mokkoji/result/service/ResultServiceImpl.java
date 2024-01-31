package online.mokkoji.result.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mokkoji.common.exception.RestApiException;
import online.mokkoji.result.domain.Result;
import online.mokkoji.result.dto.response.MemoryResDto;
import online.mokkoji.result.dto.response.RecollectionResDto;
import online.mokkoji.result.repository.ResultRepository;
import online.mokkoji.user.domain.Provider;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService{

    private final ResultRepository resultRepository;

    @Override
    public Map<String, Object> readResult(String provider, String email)  {
        List<Result> resultList = resultRepository.findAllByProviderAndEmailOrderByIdDesc(Provider.valueOf(provider), email);

        if(resultList.isEmpty())
            return null;

        Map<String, Object> resultMap = new HashMap<>();
        List<MemoryResDto> memoryList = new ArrayList<>();
        List<RecollectionResDto> recollectionList = new ArrayList<>();

        for(Result result : resultList) {
            LocalDate date  = result.getEvent().getStartTime().toLocalDate();

            if(result.getStatus().getKey().equals("memory")) {
                MemoryResDto memoryResDto = MemoryResDto.builder()
                        .date(date)
                        .participantCount(result.getEvent().getParticipantCount())
                        .isPaperEdited(result.getRollingpaper().isEdited())
                        .isMosaicCreated(result.getPhotomosaic() != null ? true : false)
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
}
