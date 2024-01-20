package online.mokkoji.db.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor  //기본 생성자 생성
@Entity
public class User extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder    //빌더 패턴 자동 생성
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKEY();
    }


    @Getter
    @RequiredArgsConstructor
    public enum Role {
        //Spring Security의 권한 코드는 항상 ROLE_이 앞에 있어야 한다
        GUEST("ROLE_GUEST", "게스트"),
        USER("ROLE_USER", "일반 사용자");
        //ADMIN("ROLE_ADMIN", "관리자")

        private final String KEY;
        private final String TITLE;
    }
}
