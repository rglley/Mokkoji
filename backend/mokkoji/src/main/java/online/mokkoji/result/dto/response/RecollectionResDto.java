package online.mokkoji.result.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class RecollectionResDto {
    private LocalDate date;
    private String image;
    private String name;
    private String content;
}
