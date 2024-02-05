package online.mokkoji.result.domain.RollingPaper;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
public class BackgroundTemplate {

    @Id
    @GeneratedValue
    @Column(name = "background_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rollingpaper_id")
    private RollingPaper rollingPaper;

    //enum이 맞지 않나
    @Column(nullable = false, length = 15)
    @Size(max = 15)
    private String name;

    @Column(nullable = false, length = 100)
    @Size(max = 100)
    private String path;

    public BackgroundTemplate(String backgroundName, String backgroundPath) {
        this.backgroundName = backgroundName;
        this.backgroundPath = backgroundPath;
    }
}
