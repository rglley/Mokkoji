package online.mokkoji.openvidu.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
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
        this.startTime = Instant.ofEpochMilli(milli).atZone(ZoneId.of("UTC")).toLocalDateTime();
    }

// TODO : 이거 없어도 돌아가는지 확인하고 삭제
//    // deleteSession에서 RequestBody로 받을 생성자
//    public SessionReqDto( int participantCount, LocalDateTime endTime) {
//        this.participantCount = participantCount;
//        this.endTime = endTime;
//    }
}