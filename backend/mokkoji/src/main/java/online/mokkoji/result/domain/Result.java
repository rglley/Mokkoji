package online.mokkoji.result.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import online.mokkoji.event.domain.Event;
import online.mokkoji.result.domain.RollingPaper.RollingPaper;
import online.mokkoji.user.domain.User;

@Entity
@Getter
@ToString(of = {"name", "content", "status"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Result {

    @Id
    @GeneratedValue
    @Column(name = "result_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.MEMORY;

    @Column(length = 15, nullable = false)
    @Size(max = 15)
    private String name;

    @Column(length = 40)
    @Size(max = 40)
    private String content;

    @Column(length = 100)
    @Size(max = 100)
    private String image;

    @OneToOne(mappedBy = "result", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, optional = false)
    private RollingPaper rollingpaper;

    @OneToOne(mappedBy = "result", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Photomosaic photomosaic;

    @Builder
    public Result(Event event) {
        this.event = event;
        event.setResult(this);
    }

    //setter는 안쓰는게 맞지 않는지?
    public void setRollingpaper(RollingPaper rollingPaper) {
        this.rollingpaper = rollingPaper;
    }
}
