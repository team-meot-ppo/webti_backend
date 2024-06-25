package org.meotppo.webti.domain.repository.jpa.statistics;


import org.meotppo.webti.domain.entity.TechRole;
import org.meotppo.webti.domain.entity.jpa.statistics.TechRoleStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TechRoleStatisticsRepository extends JpaRepository<TechRoleStatistics, Long> {
    Optional<TechRoleStatistics> findByRole(TechRole role);
}
