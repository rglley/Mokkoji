package online.mokkoji.user.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import online.mokkoji.event.domain.Event;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString(of = {"id", "email", "name", "image"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, length = 30)
    @Size(max = 30)
    private String email;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Column(nullable = false, length = 10)
    @Size(max = 10)
    private String name;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    private String image;

    @OneToMany(mappedBy = "user")
    private List<Event> events = new ArrayList<>();

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private UserAccount userAccount;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Record record;

    // TODO : reddis로 관리
    @Column(length = 100)
    @Size(max = 100)
    private String refreshToken;

    @Builder
    public User(String provider, String email, String name, String image, Authority authority, String refreshToken) {
        this.provider = Provider.valueOf(provider);
        this.email = email;
        this.name = name;
        this.image = image;
        this.authority = authority;
        this.refreshToken = refreshToken;
    }

    @Builder(builderMethodName = "testBuilder")
    public User(String email, String name, String image) {
        this.email = email;
        this.name = name;
        this.image = image;
    }

    @Builder(builderMethodName = "updateBuilder")
    public User(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
