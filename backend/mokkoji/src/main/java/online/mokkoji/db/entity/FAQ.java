package online.mokkoji.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FAQ extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "faq_id")
    private Long id;

    @Size(max = 100)
    private String question;

    @Size(max = 1000)
    private String answer;

    //==생성자==//

    public FAQ(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}
