package online.mokkoji.result.domain.RollingPaper;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BackgroundTemplate {

    @Id
    @GeneratedValue
    @Column(name = "background_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private BackgroundName backgroundName;
    private String backgroundPath;

    public BackgroundTemplate(BackgroundName backgroundName, String backgroundPath) {
        this.backgroundName = backgroundName;
        this.backgroundPath = backgroundPath;
    }
}
