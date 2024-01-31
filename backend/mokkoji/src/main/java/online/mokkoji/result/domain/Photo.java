package online.mokkoji.result.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Photo implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private Long resultId;

    //    @Column(nullable = false, length = 100)
//    @Size(max = 100)
    private String url;

    public Photo(Long resultId, String url) {
        this.resultId = resultId;
        this.url = url;
    }
}
