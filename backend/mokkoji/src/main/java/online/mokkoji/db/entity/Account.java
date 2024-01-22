package online.mokkoji.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @Column(nullable = false)
    private User user;

    @Column(nullable = false)
    @Size(max = 10)
    private String bank;

    @Column(name = "account_number", nullable = false)
    @Size(max = 20)
    private String accountNumber;



}
