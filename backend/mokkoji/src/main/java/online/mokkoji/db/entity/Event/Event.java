package online.mokkoji.db.entity.Event;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import online.mokkoji.db.entity.BaseEntity;
import online.mokkoji.db.entity.Photo;
import online.mokkoji.db.entity.User;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "participantCount", "status", "type", "content", "startTime", "endTime"})
public class Event extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "event_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "session_id")
    @Size(max = 100)
    private String sessionId;

    @ColumnDefault("0")
    @Column(name = "participant_count", nullable = false)
    private int participantCount;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("MEMORY")
    private EventStatus status;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("DEFAULT")
    private EventType type;

    @Size(max = 100)
    private String content;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    //사진 리스트
    @OneToMany(mappedBy = "event")
    private List<Photo> photos = new ArrayList<>();

    //==생성자==//

    public Event(User user, String sessionId, LocalDateTime startTime) {

        this.user = user;
        this.sessionId = sessionId;
        this.startTime = startTime;
    }
}
