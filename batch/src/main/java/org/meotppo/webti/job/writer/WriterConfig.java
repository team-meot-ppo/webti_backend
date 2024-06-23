package org.meotppo.webti.job.writer;

import org.meotppo.webti.domain.entity.jpa.statistics.TechRoleStatistics;
import org.meotppo.webti.domain.repository.jpa.statistics.TechRoleStatisticsRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WriterConfig {

    public static final String TECH_ROLE_STATISTICS_WRITER = "techRoleStatisticsWriter";

    @Bean(name = TECH_ROLE_STATISTICS_WRITER)
    public ItemWriter<TechRoleStatistics> techRoleStatisticsWriter(TechRoleStatisticsRepository techRoleStatisticsRepository) {
        return items -> {
            for (TechRoleStatistics item : items) {
                TechRoleStatistics existing = techRoleStatisticsRepository.findByRole(item.getRole())
                        .orElse(new TechRoleStatistics(item.getRole(), 0L, 0L));
                existing.updateCount(existing.getCount() + item.getCount());
                existing.updateMatchesSelfAssessmentCount(existing.getMatchesSelfAssessmentCount() + item.getMatchesSelfAssessmentCount());
                techRoleStatisticsRepository.save(existing);
            }
        };
    }
}
