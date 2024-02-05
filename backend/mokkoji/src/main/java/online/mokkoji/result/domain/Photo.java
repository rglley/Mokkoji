package online.mokkoji.result.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class Photo implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private Long resultId;

    @Column(nullable = false)
    private String photoPath;

    @Builder
    public Photo(Long resultId, String photoPath) {
        this.resultId = resultId;
        this.photoPath = photoPath;
    }
}
