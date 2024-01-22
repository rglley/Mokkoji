package online.mokkoji.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RollingpaperTemplate extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "rollingpaper_template_id")
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;


}
