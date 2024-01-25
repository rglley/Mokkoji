package online.mokkoji.api.service;

import lombok.RequiredArgsConstructor;
import online.mokkoji.api.response.ResultResDto;
import online.mokkoji.api.response.RollingpaperResDto;
import online.mokkoji.db.entity.Event.Event;
import online.mokkoji.db.entity.Result.Result;
import online.mokkoji.db.entity.RollingpaperTemplate;
import online.mokkoji.db.entity.User;
import online.mokkoji.db.repository.EventRepository;
import online.mokkoji.db.repository.ResultRepository;
import online.mokkoji.db.repository.RollingpaperTemplateRepository;
import online.mokkoji.db.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultService {

    private final ResultRepository resultRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final RollingpaperTemplateRepository rollingpaperTemplateRepository;

    public List<ResultResDto> getResults(@RequestBody String username) {
        User user = userRepository.findByName(username);
        List<Event> eventList = eventRepository.findAllByUser(user);
        List<Result> resultList = resultRepository.findAllByUser(user);

        List<ResultResDto> resultResDtoList = new ArrayList<>();

        for (Result result : resultList) {
            ResultResDto resultResDto = new ResultResDto();
            resultResDto.setUsername(user.getName());
            resultResDto.setResultName(result.getTitle());
            resultResDto.setResultContent(result.getContent());
//            resultResDto.setParticipantCount(event.getParticipantCount());
            resultResDto.setResultStatus(resultResDto.getResultStatus());
//            resultResDto.setStartTime(event.getStartTime());

            resultResDtoList.add(resultResDto);
        }
        return resultResDtoList;
    }

    public Object getRollingpaper(String username) {
        User user = userRepository.findByName(username);
        List<RollingpaperTemplate> rollingpaperTemplateList = rollingpaperTemplateRepository.findAll();

        RollingpaperResDto rollingpaperResDto = new RollingpaperResDto();
        rollingpaperResDto.setUsername(user.getName());
        rollingpaperResDto.setRollingpaperTemplateList(rollingpaperTemplateList);

        return rollingpaperResDto;
    }

}
