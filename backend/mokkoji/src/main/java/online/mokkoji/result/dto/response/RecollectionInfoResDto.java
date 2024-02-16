package online.mokkoji.result.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RecollectionInfoResDto {

    @NotBlank
    private Long resultId;

    @NotBlank
    private LocalDate date;

    @NotBlank
    private String image;

    @NotBlank
    private String name;

    @NotBlank
    private String content;

    @Builder
    public RecollectionInfoResDto(Long resultId, LocalDate date, String image, String name, String content) {
        this.resultId = resultId;
        this.date = date;
        this.image = image;
        this.name = name;
        this.content = content;
    }
}
