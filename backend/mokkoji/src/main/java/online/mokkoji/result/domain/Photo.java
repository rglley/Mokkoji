package online.mokkoji.result.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Builder
public class Photo implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private Long resultId;

    @Column(nullable = false)
    private String url;

    public Photo(Long resultId, String url) {
        this.resultId = resultId;
        this.url = url;
    }
}
