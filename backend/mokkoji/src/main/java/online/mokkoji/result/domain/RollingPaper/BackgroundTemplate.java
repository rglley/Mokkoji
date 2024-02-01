package online.mokkoji.result.domain.RollingPaper;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
public class BackgroundTemplate {

    @Id
    @GeneratedValue
    @Column(name = "background_id")
    private int id;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private BackgroundName backgroundName = BackgroundName.BASIC;
    private String backgroundPath = "url";
}
