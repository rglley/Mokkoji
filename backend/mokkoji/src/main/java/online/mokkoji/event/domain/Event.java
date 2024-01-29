package online.mokkoji.event.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import online.mokkoji.openvidu.dto.request.SessionReqDto;
import online.mokkoji.common.exception.RestApiException;
import online.mokkoji.common.exception.errorCode.EventErrorCode;
import online.mokkoji.result.domain.Result;
import online.mokkoji.user.domain.User;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Entity
@Table(name = "event")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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

    @Column(name = "session_id", length = 100)
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

    @OneToOne(mappedBy = "event", fetch = FetchType.LAZY)
    private Result result;

    //==연관관계 메서드==//
    public void setUser(User user) {
        this.user = user;
        user.getEvents().add(this);
    }

    //==생성자==//
    public Event(User user, String sessionId, LocalDateTime startTime) {
        this.builder()
                .user(user)
                .sessionId(sessionId)
                .startTime(startTime)
                .build();
    }

    //==설정 메서드==//


    //==비즈니스 로직==//


    // Session 종료 시 정보 추가, CLOSED로 변경
    public void closeSession(SessionReqDto sessionReqDto) {

        // 이미 끝나있는 세션이라면
        if (this.getStatus() == EventStatus.CLOSED) {
            throw new RestApiException(EventErrorCode.ALREADY_CLOSED_EVENT);
        }

        this.status = EventStatus.CLOSED;
        this.participantCount = sessionReqDto.getParticipantCount();
        this.endTime = sessionReqDto.getEndTime();
    }


}
