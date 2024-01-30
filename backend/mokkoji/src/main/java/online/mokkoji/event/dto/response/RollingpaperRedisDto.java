package online.mokkoji.event.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RollingpaperRedisDto {

    private Long userId;
    private Long resultId;
    private String text;
    private String voice;
    private String video;

    public RollingpaperRedisDto(Long userId, Long resultId, String text, Map<String, String> urlMap) {
        this.userId = userId;
        this.resultId = resultId;
        this.text = text;
        for (Map.Entry<String, String> urlEntry : urlMap.entrySet()) {
            if (urlEntry.getKey().equals("voice")) this.voice = urlEntry.getValue();
            else if (urlEntry.getKey().equals("video")) this.video = urlEntry.getValue();
        }
    }
}
