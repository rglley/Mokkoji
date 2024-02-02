package online.mokkoji.result.domain.RollingPaper;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PostitTemplate {

    @Id
    @GeneratedValue
    @Column(name = "postit_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private PostitName postitName;
    private String postitPath;

    public PostitTemplate(PostitName postitName, String postitPath) {
        this.postitName = postitName;
        this.postitPath = postitPath;
    }
}