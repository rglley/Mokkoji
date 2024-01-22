package online.mokkoji.db.entity.Event;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import online.mokkoji.db.entity.BaseEntity;
import online.mokkoji.db.entity.Survey.Survey;
import online.mokkoji.db.entity.User;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "event_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ColumnDefault("0")
    @Column(name = "participant_count", nullable = false)
    private int participantCount;

    @ColumnDefault("MEMORY")
    private EventStatus status;

    @ColumnDefault("DEFAULT")
    private EventType type;

    @Size(max = 100)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "end_time")
    private LocalDateTime endTime;

    //결과 설문 List
    @OneToMany(mappedBy = "event")
    private List<Survey> surveys = new ArrayList<>();
}
