package online.mokkoji.db.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import online.mokkoji.db.entity.Event.Event;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "thumbnail", "path"})
public class Photo extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "photo_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @ColumnDefault("false")
    private Boolean thumbnail;

    //S3 업로드 경로
    private String path;

    //==생성자==//

}
