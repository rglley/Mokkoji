package online.mokkoji.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Entity
@Getter
public class CongratulationMessage {

    @Id
    @GeneratedValue
    @Column(name = "congradulation_message_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rollingpaper_id")
    private Rollingpaper rollingpaper;

    @Size(max = 10)
    private String writer;

    @Size(max = 200)
    private String text;

    private String voice;

    private String video;
}
