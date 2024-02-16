package online.mokkoji.result.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemoryInfoResDto {

    @NotBlank
    private Long resultId;

    @NotBlank
    private LocalDate date;

    @NotBlank
    private int participantCount;

    @NotBlank
    private boolean isPaperEdited;

    @NotBlank
    private boolean isMosaicCreated;

    @Builder
    public MemoryInfoResDto(Long resultId, LocalDate date, int participantCount, boolean isPaperEdited, boolean isMosaicCreated) {
        this.resultId = resultId;
        this.date = date;
        this.participantCount = participantCount;
        this.isPaperEdited = isPaperEdited;
        this.isMosaicCreated = isMosaicCreated;
    }
}
