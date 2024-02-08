package online.mokkoji.result.domain.RollingPaper;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public Message(Long paperId, String writer, @Nullable String text, @Nullable String voicePath, @Nullable String videoPath) {
        this.paperId = paperId;
        this.writer = writer;
        this.text = text;
        this.voicePath = voicePath;
        this.videoPath = videoPath;
    }
}
