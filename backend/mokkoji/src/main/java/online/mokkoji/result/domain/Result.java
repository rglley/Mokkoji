package online.mokkoji.result.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import online.mokkoji.event.domain.Event;
import online.mokkoji.user.domain.User;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Getter
@ToString(of = {"title", "content", "status"})
public class Result {

    @Id
    @GeneratedValue
    @Column(name = "result_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(nullable = false, length = 15)
    @Size(max = 15)
    private String title;

    @Size(max = 40)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.MEMORY;

    @OneToOne(mappedBy = "result", fetch = FetchType.LAZY, optional = false)
    private RollingPaper rollingpaper;

    @OneToOne(mappedBy = "result", fetch = FetchType.LAZY)
    private Photomosaic photomosaic;

    @OneToMany(mappedBy = "result")
    private List<Photo> photoList;
}
