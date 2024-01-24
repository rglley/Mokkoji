package online.mokkoji.db.entity.Event;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import online.mokkoji.db.entity.User;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Entity
@Table(name = "event")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@ToString(of = {"id", "participantCount", "status", "type", "content", "startTime", "endTime"})
public class Event/* extends BaseEntity */ {

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
    @Column(name = "participant_count")
    private int participantCount;

    @Column(name = "event_status")
    @Enumerated(EnumType.STRING)
    private EventStatus eventStatu

    @Size(max = 15)
    private String title;

    @Size(max = 40)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_status")
//    @ColumnDefault("MEMORY")
    private ResultStatus resultStatus;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;


    //==생성자==//

    public Event(User user, String sessionId, LocalDateTime startTime) {
        this.user = user;
        this.sessionId = sessionId;
        this.startTime = startTime;
    }
}
