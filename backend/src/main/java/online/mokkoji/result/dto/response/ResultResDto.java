package online.mokkoji.result.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import online.mokkoji.result.domain.rollingpaper.Message;
import org.springframework.data.domain.Page;

@Getter
public class ResultResDto {

    @NotBlank
    private String backgroundTemplate;

    @NotBlank
    private String postitTemplate;

    @NotBlank
    private Page<Message> messageList;

    @NotBlank
    private int totalPage;

    @NotBlank
    private int participantCount;

    @NotBlank
    private int messageCount;

    @NotBlank
    private String thumbnail;

    private String name;
    private String content;
    private String photomosaic;

    @Builder
    public ResultResDto(String backgroundTemplate, String postitTemplate, Page<Message> messageList, int totalPage,
                        String thumbnail, String name, String content, int participantCount, int messageCount, String photomosaic) {
        this.backgroundTemplate = backgroundTemplate;
        this.postitTemplate = postitTemplate;
        this.messageList = messageList;
        this.totalPage = totalPage;
        this.thumbnail = thumbnail;
        this.name = name;
        this.content = content;
        this.participantCount = participantCount;
        this.messageCount = messageCount;
        this.photomosaic = photomosaic;
    }
}
