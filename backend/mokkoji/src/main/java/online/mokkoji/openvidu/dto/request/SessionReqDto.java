package online.mokkoji.openvidu.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@NoArgsConstructor
public class SessionReqDto {
    private Long userId;
    private String sessionId;
    private LocalDateTime startTime;

    private int participantCount;
    private LocalDateTime endTime;


    // Session 생성용 생성자
    public SessionReqDto(Long userId, String sessionId, Long milli) {
        this.userId = userId;
        this.sessionId = sessionId;

        // millisceconds -> LocalDateTime-UTC으로 변경
        LocalDateTime createdAt = Instant.ofEpochMilli(milli).atZone(ZoneId.of("UTC")).toLocalDateTime();

        this.startTime = createdAt;
    }


    // deleteSession에서 RequestBody로 받을 생성자
    public SessionReqDto(Long userId, int participantCount, LocalDateTime endTime) {
        this.userId = userId;
        this.participantCount = participantCount;
        this.endTime = endTime;
    }
}