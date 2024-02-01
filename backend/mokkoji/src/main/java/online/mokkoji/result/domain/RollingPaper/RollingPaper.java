package online.mokkoji.result.domain.RollingPaper;

import jakarta.persistence.*;
import lombok.*;
import online.mokkoji.result.domain.Result;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class RollingPaper {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "result_id")
    private Result result;

    @Embedded
    private BackgroundTemplate backgroundTemplate;

    @Embedded
    private PostitTemplate postitTemplate;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean isEdited;


    @Builder(builderMethodName = "buildWithResult")
    public RollingPaper(Result result) {
        this.result = result;
        this.backgroundTemplate = new BackgroundTemplate(BackgroundName.BASIC, "url"); //TODO : 진짜 url 넣기
        this.postitTemplate = new PostitTemplate(PostitName.RAINBOW, "url"); //TODO : 진짜 url 넣기
        result.setRollingpaper(this);
    }
}
