package online.mokkoji.result.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class MemoryResDto {
    private LocalDate date;
    private int participantCount;
//    private String location;
    private boolean isPaperEdited;
    private boolean isMosaicCreated;
}
