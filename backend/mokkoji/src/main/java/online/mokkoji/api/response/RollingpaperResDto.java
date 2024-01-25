package online.mokkoji.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import online.mokkoji.db.entity.RollingpaperTemplate;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RollingpaperResDto {
    private String username;
    private List<RollingpaperTemplate> rollingpaperTemplateList;
}
