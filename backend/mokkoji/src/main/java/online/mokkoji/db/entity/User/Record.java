package online.mokkoji.db.entity.User;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(of = {"id", "eventCount", "totalTime", "totalParticipant", "totalMessage"})
public class Record {

    @Id
    @GeneratedValue
    @Column(name = "record_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int eventCount;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int totalTime;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int totalParticipant;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int totalMessage;

    public Record(User user, int eventCount, int totalTime, int totalParticipant, int totalMessage) {
        this.builder()
                .user(user)
                .eventCount(eventCount)
                .totalTime(totalTime)
                .totalParticipant(totalParticipant)
                .totalMessage(totalMessage)
                .build();
    }
}
