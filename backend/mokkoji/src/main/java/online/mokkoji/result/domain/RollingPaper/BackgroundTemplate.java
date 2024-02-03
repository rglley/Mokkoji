package online.mokkoji.result.domain.RollingPaper;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
public class BackgroundTemplate {

    @Id
    @GeneratedValue
    @Column(name = "background_id")
    private int id;

    private String backgroundName;
    private String backgroundPath;

}
