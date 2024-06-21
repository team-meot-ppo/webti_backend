package org.meotppo.webti.domain.entity.jpa;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.meotppo.webti.domain.entity.TechRole;

@Getter
@Entity
@NoArgsConstructor
public class TechRoleStatistics {  // DataBase 테스트용 TODO. 수정해야함

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private TechRole role;

    @Column(nullable = false)
    private Long count;

    @Column(nullable = false)
    private Long matchesSelfAssessmentCount;

    @Builder
    public TechRoleStatistics(TechRole role, Long count, Long matchesSelfAssessmentCount) {
        this.role = role;
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
