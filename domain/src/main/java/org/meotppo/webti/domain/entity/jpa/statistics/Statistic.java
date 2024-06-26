package org.meotppo.webti.domain.entity.jpa.statistics;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.meotppo.webti.domain.entity.jpa.common.JpaEntityDate;
import org.meotppo.webti.domain.entity.jpa.developerProfile.WebDeveloperProfile;

@Getter
@Entity
@NoArgsConstructor
public class Statistic extends JpaEntityDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "developer_profile_id", nullable = false)
    private WebDeveloperProfile developerProfile;

    @Column(nullable = false)
    private Long count;

    @Column(nullable = false)
    private Long matchCount;

    @Builder
    public Statistic(WebDeveloperProfile developerProfile, Long count, Long matchCount) {
        this.developerProfile = developerProfile;
        this.count = count;
        this.matchCount = matchCount;
    }

    public void updateCount(Long count) {
        this.count = count;
    }

    public void updateMatchCount(Long matchCount) {
        this.matchCount = matchCount;
    }
}
