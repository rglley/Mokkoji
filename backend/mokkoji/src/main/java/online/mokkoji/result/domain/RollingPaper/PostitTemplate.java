package online.mokkoji.result.domain.RollingPaper;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Entity
public class PostitTemplate {

    @Id
    @GeneratedValue
    @Column(name = "postit_id")
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
}