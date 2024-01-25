package online.mokkoji.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultResDto {

    private String username;
    private String resultName;
    private String resultContent;
    private String resultStatus;
    private int participantCount;
    private LocalDateTime startTime;
//    private String rollingpaperStatus;

}
