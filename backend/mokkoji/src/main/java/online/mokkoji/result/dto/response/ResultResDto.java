package online.mokkoji.result.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import online.mokkoji.result.domain.RollingPaper.Message;
import org.springframework.data.domain.Page;

@Getter
public class ResultResDto {

    @NotBlank
    private String backgroundTemplate;

    @NotBlank
    private String postitTemplate;

    @NotBlank
    private Page<Message> messageList;

    private String photomosaic;

    @Builder
    public ResultResDto(String backgroundTemplate, String postitTemplate, Page<Message> messageList, String photomosaic) {
        this.backgroundTemplate = backgroundTemplate;
        this.postitTemplate = postitTemplate;
        this.messageList = messageList;
        this.photomosaic = photomosaic;
    }
}
