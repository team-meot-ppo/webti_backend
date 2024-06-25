package org.meotppo.webti.domain.repository.jpa.statistics;

import org.meotppo.webti.domain.entity.jpa.statistics.Statistic;
import org.meotppo.webti.domain.entity.jpa.developerProfile.WebDeveloperProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {
    Optional<Statistic> findByDeveloperProfile(WebDeveloperProfile developerProfile);
}
