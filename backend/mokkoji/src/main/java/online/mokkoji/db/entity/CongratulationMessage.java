package online.mokkoji.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "writer", "text", "voice", "video"})
public class CongratulationMessage extends BaseEntity {

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

    //==생성자==//
}
