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
@Table(name = "users") //h2만 user 사용 불가능
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "email", "name", "image"})
public class User/* extends BaseEntity*/ {

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


    //행사 리스트
    @OneToMany(mappedBy = "user")
    private List<Event> events = new ArrayList<>();

    //활동 기록
    @OneToMany(mappedBy = "user")
    private List<Record> records = new ArrayList<>();


    //==생성자==//
    public User(String email, String name, String image) {
        this.email = email;
        this.name = name;
        this.image = image;
    }
}
