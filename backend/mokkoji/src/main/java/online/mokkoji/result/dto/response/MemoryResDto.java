package online.mokkoji.result.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class MemoryResDto {

    @NotBlank
    private Long id;

    @NotBlank
    private LocalDate date;

    @NotBlank
    private int participantCount;

    @NotBlank
    private boolean isPaperEdited;

    @NotBlank
    private boolean isMosaicCreated;

    @Builder
    public MemoryResDto (Long id, LocalDate date, int participantCount, boolean isPaperEdited, boolean isMosaicCreated) {
        this.id = id;
        this.date = date;
        this.participantCount = participantCount;
        this.isPaperEdited = isPaperEdited;
        this.isMosaicCreated = isMosaicCreated;
    }
}
