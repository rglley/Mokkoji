package online.mokkoji.user.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import online.mokkoji.event.domain.Event;

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

    //enum이 낫지 않을까?
    @Enumerated(EnumType.STRING)
    private Role role;

    private String image;

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<Event> events = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private UserAccount userAccount;

    @OneToOne(mappedBy = "user")
    private Record record;

    //차후 Reddis로 넣을 예정
    @Size(max = 100)
    private String refreshToken;

    public User(String provider, String email, String name, String image, Role role) {
        this.builder()
                .provider(Provider.valueOf(provider))
                .email(email)
                .name(name)
                .image(image)
                .role(role)
                .build();
    }

    public User(String provider, String email, String name, String image, Role role, String refreshToken) {
        this.builder()
                .provider(Provider.valueOf(provider))
                .email(email)
                .name(name)
                .image(image)
                .role(role)
                .refreshToken(refreshToken)
                .build();
    }

    public User(String email, String name, String image) {
        this.builder()
                .email(email)
                .name(name)
                .image(image)
                .build();
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void updateUser(String provider, String email, String name, String image, Role role) {
        this.provider = Provider.valueOf(provider);
        this.email = email;
        this.name = name;
        this.image = image;
        this.role = role;


    }
}
