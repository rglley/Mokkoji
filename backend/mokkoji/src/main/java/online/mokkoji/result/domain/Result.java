package online.mokkoji.result.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import online.mokkoji.event.domain.Event;
import online.mokkoji.user.domain.User;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"name", "content", "status"})
public class Result {

    @Id
    @GeneratedValue
    @Column(name = "result_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(nullable = false, length = 15)
    @Size(max = 15)
    private String name;

    @Column(length = 40)
    @Size(max = 40)
    private String content;

    @Column(nullable = false, length = 100)
    @Size(max = 100)
    private String image;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.MEMORY;

    @OneToOne(mappedBy = "result", fetch = FetchType.LAZY, optional = false)
    private RollingPaper rollingpaper;

    @OneToOne(mappedBy = "result", fetch = FetchType.LAZY)
    private Photomosaic photomosaic;

    @OneToMany(mappedBy = "resultId")
    private List<Photo> photoList;

    public Result(Event event) {
        this.event = event;
    }


    public void setRollingpaper(RollingPaper rollingPaper) {
        this.rollingpaper = rollingPaper;
    }
}
