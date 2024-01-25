package online.mokkoji.db.entity.Event;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import online.mokkoji.api.request.SessionReqDto;
import online.mokkoji.db.entity.Result.Result;
import online.mokkoji.db.entity.User;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Entity
@Table(name = "event")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@ToString(of = {"id", "participantCount", "status", "startTime", "endTime"})
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

    @Enumerated(EnumType.STRING)
    private EventStatus status = EventStatus.ACTIVE;


    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;


    //==연관관계 메서드==//
    public void setUser(User user) {
        this.user = user;
        user.getEvents().add(this);
    }

    //==생성자==//
    public Event(User user, String sessionId, LocalDateTime startTime) {
        this.setUser(user);
        this.sessionId = sessionId;
        this.startTime = startTime;
        Result result = new Result(this);
    }

    //==설정 메서드==//


    //==비즈니스 로직==//


    // Session 종료 시 정보 추가, CLOSED로 변경
    public void closeSession(SessionReqDto sessionReqDto) {

        // 이미 끝나있는 세션이라면
        if (this.getStatus() == EventStatus.CLOSED) {
            throw new IllegalStateException("이미 끝난 세션입니다.");
        }

        this.status = EventStatus.CLOSED;
        this.participantCount = sessionReqDto.getParticipantCount();
        this.endTime = sessionReqDto.getEndTime();
    }


}
