package online.mokkoji.db.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Record extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "record_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "event_count", nullable = false)
    private int eventCount;

    @Column(name = "total_time", nullable = false)
    private int totalTime;

    @Column(name = "total_participant", nullable = false)
    private int totalParticipant;

    @Column(name = "total_message", nullable = false)
    private int totalMessage;


}
