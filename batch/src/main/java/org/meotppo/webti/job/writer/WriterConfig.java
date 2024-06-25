package org.meotppo.webti.job.writer;

import org.meotppo.webti.domain.entity.jpa.statistics.Statistic;
import org.meotppo.webti.domain.repository.jpa.statistics.StatisticRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WriterConfig {

    public static final String STATISTIC_WRITER = "statisticWriter";

    @Bean(name = STATISTIC_WRITER)
    public ItemWriter<Statistic> statisticWriter(StatisticRepository statisticRepository) {
        return items -> {
            for (Statistic item : items) {
                Statistic existing = statisticRepository.findByDeveloperProfile(item.getDeveloperProfile())
                        .orElse(new Statistic(item.getDeveloperProfile(), 0L, 0L));
                existing.updateCount(existing.getCount() + item.getCount());
                existing.updateMatchCount(existing.getMatchCount() + item.getMatchCount());
                statisticRepository.save(existing);
            }
        };
    }
}
