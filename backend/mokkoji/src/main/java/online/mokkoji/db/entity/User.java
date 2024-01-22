package online.mokkoji.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import online.mokkoji.db.entity.Event.Event;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "email", "name", "image"})
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Size(max = 30)
    @Column(nullable = false)
    private String email;

    @Size(max = 10)
    @Column(nullable = false)
    private String name;

    private String image;

    //계좌
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Account account;

    //행사 리스트
    @OneToMany(mappedBy = "user")
    private List<Event> events = new ArrayList<>();

    //활동 기록
    @OneToMany(mappedBy = "user")
    private List<Record> records = new ArrayList<>();
}
