package online.mokkoji.result.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RecollectionResDto {

    @NotBlank
    private Long id;

    @NotBlank
    private LocalDate date;

    @NotBlank
    private String image;

    @NotBlank
    private String name;

    @NotBlank
    private String content;

    @Builder
    public RecollectionResDto(Long id, LocalDate date, String image, String name, String content) {
        this.id = id;
        this.date = date;
        this.image = image;
        this.name = name;
        this.content = content;
    }
}
