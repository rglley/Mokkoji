package online.mokkoji.result.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Photomosaic  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photomosaic_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "result_id")
    private Result result;

    @Column(length = 100)
    @Size(max = 100)
    private String path;

    @Builder
    public Photomosaic(Result result, String path) {
        this.result = result;
        this.path = path;
    }

    public void updatePhotomosaic(String path) {
        this.path = path;
    }
}

