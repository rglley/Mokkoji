package online.mokkoji.result.domain.RollingPaper;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BackgroundTemplate {

    @Enumerated(EnumType.STRING)
    private BackgroundName backgroundName;
    private String backgroundPath;

    
}
