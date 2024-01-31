package online.mokkoji.result.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import online.mokkoji.event.domain.Event;
import online.mokkoji.user.domain.User;

@Entity
@NoArgsConstructor
@ToString(of = {"title", "content", "status"})
@Getter
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

    @Size(max = 15)
    private String title;

    @Size(max = 40)
    private String content;

    @Enumerated(EnumType.STRING)
    private ResultStatus status = ResultStatus.MEMORY;

    // 롤링페이퍼 리스트 -> 엔티티 생겨야 활성화 가능
    @OneToOne(mappedBy = "result", fetch = FetchType.LAZY)
    private RollingPaper rollingpaper;

    // 포토모자이크 -> 엔티티 생겨야 활성화 가능
//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "result")
//    private Photomosaic photomosaic;

    // 사진 -> 엔티티 생겨야 활성화 가능
//    @ManyToOne(fetch = FetchType.LAZY, mappedBy = "result")
//    private List<Photo> photoList=new ArrayList<>();


    public Result(Event event) {
        this.event = event;
    }

    
    public void setRollingpaper(RollingPaper rollingPaper) {
        this.rollingpaper = rollingPaper;
    }
}
