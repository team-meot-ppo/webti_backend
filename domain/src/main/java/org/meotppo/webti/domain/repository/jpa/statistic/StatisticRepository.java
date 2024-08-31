package org.meotppo.webti.domain.repository.jpa.statistic;

import java.util.Optional;
import org.meotppo.webti.domain.entity.jpa.developerprofile.WebDeveloperProfile;
import org.meotppo.webti.domain.entity.jpa.statistic.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticRepository extends JpaRepository<Statistic, Long>, QuerydslStatisticRepository {
    Optional<Statistic> findByDeveloperProfile(WebDeveloperProfile developerProfile);
}
