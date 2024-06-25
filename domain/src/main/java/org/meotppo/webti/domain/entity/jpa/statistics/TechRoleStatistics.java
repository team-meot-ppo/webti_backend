package org.meotppo.webti.domain.entity.jpa.statistics;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.meotppo.webti.domain.entity.TechRole;
import org.meotppo.webti.domain.entity.jpa.common.JpaEntityDate;

@Getter
@Entity
@NoArgsConstructor
public class TechRoleStatistics extends JpaEntityDate {

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
