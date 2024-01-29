package online.mokkoji.db.entity.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToOne(mappedBy = "user")
    private Account account;

    @OneToOne(mappedBy = "user")
    private Record record;

    //차후 Reddis로 넣을 예정
    @Size(max = 100)
    private String refreshToken;

    public User toEntity(String provider, String email, String name, String image, Role role) {
        return User.builder()
                .provider(Provider.valueOf(provider))
                .email(email)
                .name(name)
                .image(image)
                .role(role)
                .build();
    }

    public User toEntity(String provider, String email, String name, String image, Role role, String refreshToken) {
        return User.builder()
                .provider(Provider.valueOf(provider))
                .email(email)
                .name(name)
                .image(image)
                .role(role)
                .refreshToken(refreshToken)
                .build();
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
