package online.mokkoji.result.domain.RollingPaper;

import jakarta.persistence.*;
import lombok.*;
import online.mokkoji.result.domain.Result;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class RollingPaper {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "result_id")
    private Result result;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "background_id")
    private BackgroundTemplate backgroundTemplate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postit_id")
    private PostitTemplate postitTemplate;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean isEdited;

    public RollingPaper(Result result) {
        this.builder()
                .result(result)
                .backgroundTemplate(new BackgroundTemplate())
                .postitTemplate(new PostitTemplate())
                .build();
        result.setRollingpaper(this);
    }
}
