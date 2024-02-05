package online.mokkoji.result.domain.RollingPaper;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class BackgroundTemplate {

    @Id
    @GeneratedValue
    @Column(name = "background_id")
    private int id;

    @Enumerated(EnumType.STRING)
    private BackgroundName backgroundName;
    private String backgroundPath;

    public BackgroundTemplate(BackgroundName backgroundName, String backgroundPath) {
        this.backgroundName = backgroundName;
        this.backgroundPath = backgroundPath;
    }
}
