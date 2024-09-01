package org.meotppo.webti.fixture;

import org.meotppo.webti.domain.entity.jpa.profile.Profile;
import org.meotppo.webti.domain.entity.jpa.result.Statistic;

public class StatisticFixture {
    public static Statistic createStatistic(Profile profile, long count, long matchCount) {
        return Statistic.builder()
                .profile(profile)
                .count(count)
                .matchCount(matchCount)
                .build();
    }
}
