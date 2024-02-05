package online.mokkoji.result.domain.RollingPaper;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
public class BackgroundTemplate {

    @Id
    @GeneratedValue
    @Column(name = "background_id")
    private int id;

    private String backgroundName;
    private String backgroundPath;

    public BackgroundTemplate(String backgroundName, String backgroundPath) {
        this.backgroundName = backgroundName;
        this.backgroundPath = backgroundPath;
    }
}
