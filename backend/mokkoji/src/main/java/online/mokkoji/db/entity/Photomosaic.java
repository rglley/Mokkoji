package online.mokkoji.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import online.mokkoji.db.entity.Event.Event;

@Entity
@Getter
public class Photomosaic extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "photomosaic_ic")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    //S3 업로드 경로
    private String path;
}
