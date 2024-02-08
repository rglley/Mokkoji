package online.mokkoji.result.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "photo_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "result_id")
    private Result result;

    @Column(nullable = false)
    private String photoPath;

    @Builder
    public Photo(Result result, String photoPath) {
        this.result=result;
        this.photoPath = photoPath;
        result.getPhotos().add(this);
    }
}
