package online.mokkoji.result.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
import java.util.Map;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class Message implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "message_id")
    private Long id;
    private Long paperId;
    private String writer;
    @Nullable
    private String text;
    @Nullable
    private String voicePath;
    @Nullable
    private String videoPath;

    public Message(Long paperId, String writer, String text, Map<String, String> urlMap) {
        this.paperId = paperId;
        this.writer = writer;
        this.text = text;
        for (Map.Entry<String, String> urlEntry : urlMap.entrySet()) {
            if (urlEntry.getKey().equals("voice")) this.voicePath = urlEntry.getValue();
            else if (urlEntry.getKey().equals("video")) this.videoPath = urlEntry.getValue();
        }
    }
    

}
