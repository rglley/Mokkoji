package online.mokkoji.user.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class UserAccount {

    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    @Size(max = 10)
    private String bank;

    @Column(nullable = false)
    @Size(max = 20)
    private String number;

    public UserAccount toEntity(User user, String bank, String number) {
        return UserAccount.builder()
                .user(user)
                .bank(bank)
                .number(number)
                .build();
    }
}
