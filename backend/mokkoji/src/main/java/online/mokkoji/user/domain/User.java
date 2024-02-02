package online.mokkoji.user.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
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

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private UserAccount userAccount;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Record record;

    // TODO : reddis로 관리
    @Column(length = 100)
    @Size(max = 100)
    private String refreshToken;

    @Builder(builderMethodName = "nonTokenBuilder")
    public User(Provider provider, String email, String name, String image, Authority authority) {
        this.provider = provider;
        this.email = email;
        this.name = name;
        this.image = image;
        this.authority = authority;
    }

    @Builder
    public User(Provider provider, String email, String name, String image, Authority authority, String refreshToken) {
        this.provider = provider;
        this.email = email;
        this.name = name;
        this.image = image;
        this.authority = authority;
        this.refreshToken = refreshToken;
    }

    public void updateUser(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
