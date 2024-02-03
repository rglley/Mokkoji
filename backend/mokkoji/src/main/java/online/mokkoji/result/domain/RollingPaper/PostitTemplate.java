package online.mokkoji.result.domain.RollingPaper;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class PostitTemplate {

    @Id
    @GeneratedValue
    @Column(name = "postit_id")
    private int id;

    private String postitName;
    private String postitPath;

}