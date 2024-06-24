package org.meotppo.webti.job.processor;

import org.meotppo.webti.domain.entity.jpa.statistics.TechRoleStatistics;
import org.meotppo.webti.domain.entity.mongo.statistics.TechRoleTestResult;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessorConfig {

    public static final String TECH_ROLE_STATISTICS_PROCESSOR = "techRoleStatisticsProcessor";

    @Bean(name = TECH_ROLE_STATISTICS_PROCESSOR)
    public ItemProcessor<TechRoleTestResult, TechRoleStatistics> techRoleStatisticsProcessor() {
        return item -> TechRoleStatistics.builder()
                .role(item.getResult())
                .count(1L)
                .matchesSelfAssessmentCount(item.isMatchesSelfAssessment() ? 1L : 0L)
                .build();
    }
}
