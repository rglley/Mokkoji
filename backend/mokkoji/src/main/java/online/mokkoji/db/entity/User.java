package online.mokkoji.db.entity;

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
}
