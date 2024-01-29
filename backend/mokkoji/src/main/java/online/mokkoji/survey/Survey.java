package online.mokkoji.survey;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import online.mokkoji.common.domain.BaseEntity;
import online.mokkoji.event.domain.Event;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "level", "descrition"})
public class Survey extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "survey_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @Enumerated(EnumType.STRING)
    private SurveyLevel level;

    private String descrition;

    //==생성자==//
}

