package org.meotppo.webti.domain.entity.jpa.statistics;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.meotppo.webti.domain.entity.jpa.common.EntityDate;

@Getter
@Entity
@NoArgsConstructor
public class TechRoleStatistics extends EntityDate {  // DataBase 테스트용 TODO. 수정해야함

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long count;

    @Column(nullable = false)
    private Long matchesSelfAssessmentCount;

    @Builder
    public TechRoleStatistics(Long count, Long matchesSelfAssessmentCount) {
        this.count = count;
        this.matchesSelfAssessmentCount = matchesSelfAssessmentCount;
    }

    public void updateCount(Long count) {
        this.count = count;
    }

    public void updateMatchesSelfAssessmentCount(Long matchesSelfAssessmentCount) {
        this.matchesSelfAssessmentCount = matchesSelfAssessmentCount;
    }
}
