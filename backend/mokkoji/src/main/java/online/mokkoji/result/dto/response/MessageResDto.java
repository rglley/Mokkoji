package online.mokkoji.result.dto.response;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class MessageResDto {

    private Long paperId;
    private String writer;
    @Nullable
    private String text;
    @Nullable
    private String voicePath;
    @Nullable
    private String videoPath;

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
