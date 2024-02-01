package online.mokkoji.result.domain.RollingPaper;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class PostitTemplate {

    @Enumerated(EnumType.STRING)
    private PostitName postitName;
    private String postitPath;
}