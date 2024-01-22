package online.mokkoji.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import online.mokkoji.db.entity.Event.Event;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Rollingpaper extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "rollingpaper_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @ColumnDefault("false")
    private Boolean edit;

    //축하 메시지 리스트
    @OneToMany(mappedBy = "rollingpaper")
    private List<CongratulationMessage> messages = new ArrayList<>();
}
