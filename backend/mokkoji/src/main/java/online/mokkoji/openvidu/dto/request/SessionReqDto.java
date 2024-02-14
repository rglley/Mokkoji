package online.mokkoji.openvidu.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.cglib.core.Local;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SessionReqDto {
    private Long userId;
    private String sessionId;
    private LocalDateTime startTime;

    private int participantCount;

    // Session 생성용 생성자
    public SessionReqDto(Long userId, String sessionId) {
        this.userId = userId;
        this.sessionId = sessionId;
        this.startTime = LocalDateTime.now();
    }

}