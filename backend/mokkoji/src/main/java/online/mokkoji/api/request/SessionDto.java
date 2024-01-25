package online.mokkoji.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@NoArgsConstructor
public class SessionDto {
    private Long userId;
    private String sessionId;
    private LocalDateTime createdAt;

    public SessionDto(Long userId, String sessionId, Long milli) {
        this.userId = userId;
        this.sessionId = sessionId;

        // millisceconds -> LocalDateTime-UTC으로 변경
        LocalDateTime createdAt = Instant.ofEpochMilli(milli).atZone(ZoneId.of("UTC")).toLocalDateTime();

        this.createdAt = createdAt;
    }
}