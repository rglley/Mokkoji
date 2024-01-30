package online.mokkoji.user.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users") //h2만 user 사용 불가능
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(of = {"id", "email", "name", "image"})
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    @Size(max = 30)
    private String email;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Column(nullable = false)
    @Size(max = 10)
    private String name;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    private String image;

//    @OneToMany(mappedBy = "user")
//    private List<Event> events;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private UserAccount userAccount;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Record record;

    // TODO : reddis로 관리
    @Size(max = 100)
    private String refreshToken;

    // TODO : 2024.01.29 생성자->빌더로 refactoring 필요
    public User(String provider, String email, String name, String image, Authority authority) {
        this.builder()
                .provider(Provider.valueOf(provider))
                .email(email)
                .name(name)
                .image(image)
                .authority(authority)
                .build();
    }

    public User(String provider, String email, String name, String image, Authority authority, String refreshToken) {
        this.builder()
                .provider(Provider.valueOf(provider))
                .email(email)
                .name(name)
                .image(image)
                .authority(authority)
                .refreshToken(refreshToken)
                .build();
    }

    public User(String email, String name, String image) {
        this.email = email;
        this.name = name;
        this.image = image;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void updateUser(String provider, String email, String name, String image, Authority authority) {
        this.provider = Provider.valueOf(provider);
        this.email = email;
        this.name = name;
        this.image = image;
        this.authority = authority;
    }
}
