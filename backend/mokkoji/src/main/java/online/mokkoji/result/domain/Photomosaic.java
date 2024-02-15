package online.mokkoji.result.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import online.mokkoji.common.domain.BaseEntity;

@Entity
@Getter
public class Photomosaic extends BaseEntity {

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

    public void updatePath(String path) {
        this.path = path;
    }
}

