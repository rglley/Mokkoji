package online.mokkoji.result.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Entity
@Getter
public class Photomosaic {

    @Id
    @GeneratedValue
    @Column(name = "photomosaic_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "result_id")
    private Result result;

    @Column(nullable = false, length = 100)
    @Size(max = 100)
    private String path;
}

