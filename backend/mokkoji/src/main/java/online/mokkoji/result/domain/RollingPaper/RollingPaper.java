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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "background_id")
    private BackgroundTemplate backgroundTemplate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postit_id")
    private PostitTemplate postitTemplate;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean isEdited;


    @Builder(builderMethodName = "buildWithResult")
    public RollingPaper(Result result, BackgroundTemplate backgroundTemplate, PostitTemplate postitTemplate) {
        this.result = result;
        setBackgroundTemplate(backgroundTemplate);
        setPostitTemplate(postitTemplate);
        result.setRollingpaper(this);

    }

    public void setBackgroundTemplate(BackgroundTemplate backgroundTemplate) {
        this.backgroundTemplate = backgroundTemplate;
    }

    public void setPostitTemplate(PostitTemplate postitTemplate) {
        this.postitTemplate = postitTemplate;
    }
}
