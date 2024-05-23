package online.mokkoji.result.dto.response;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
public class MessageResDto {

    @NotBlank
    private Long paperId;

    @NotBlank
    private String writer;

    private String text;
    private String voicePath;
    private String videoPath;

    @Builder
    public MessageResDto(Long paperId, String writer, String text, Map<String, String> urlMap) {
        this.paperId = paperId;
        this.writer = writer;
        this.text = text;
        for (Map.Entry<String, String> urlEntry : urlMap.entrySet()) {
            if (urlEntry.getKey().equals("voice")) this.voicePath = urlEntry.getValue();
            else if (urlEntry.getKey().equals("video")) this.videoPath = urlEntry.getValue();
        }
    }
}
