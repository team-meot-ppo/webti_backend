package org.meotppo.webti.job.writer;

import org.meotppo.webti.domain.entity.jpa.result.Statistic;
import org.meotppo.webti.domain.repository.jpa.statistic.StatisticRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WriterConfig {

    public static final String STATISTIC_WRITER = "statisticWriter";

    @Bean(name = STATISTIC_WRITER)
    public ItemWriter<Statistic> statisticWriter(StatisticRepository statisticRepository) {
        return items -> items.forEach(item -> {
            Statistic existing = statisticRepository.findByProfile(item.getProfile())
                    .orElseThrow(() -> new RuntimeException(
                            "Statistic not found for developer profile: " + item.getProfile()));
            existing.updateCount(existing.getCount() + item.getCount());
            existing.updateMatchCount(existing.getMatchCount() + item.getMatchCount());
        });
    }
}
