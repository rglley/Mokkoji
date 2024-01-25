package online.mokkoji.db.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class RollingpaperTemplate extends BaseEntity{

    @Id
    @GeneratedValue
    private Long id;

    @Size(max = 100)
    private String backgroundPath;

    @Size(max = 100)
    private String postitPath;
}
