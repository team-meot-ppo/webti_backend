package org.meotppo.webti.job;

import org.meotppo.webti.domain.entity.jpa.statistics.TechRoleStatistics;
import org.meotppo.webti.domain.entity.mongo.statistics.TechPreferenceTestResult;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoPagingItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import static org.meotppo.webti.config.TransactionManagerConfig.DOMAIN_TRANSACTION_MANAGER;
import static org.meotppo.webti.job.processor.ProcessorConfig.TECH_ROLE_STATISTICS_PROCESSOR;
import static org.meotppo.webti.job.reader.ReaderConfig.TECH_PREFERENCE_TEST_RESULT_READER;
import static org.meotppo.webti.job.writer.WriterConfig.TECH_ROLE_STATISTICS_WRITER;

@Configuration
public class TechRoleStatisticsJobConfig {  // TODO. 통계 로직의 성능 개선

    public static final String TECH_ROLE_STATISTICS_JOB = "techRoleStatisticsJob";
    public static final String TECH_ROLE_STATISTICS_STEP = "techRoleStatisticsStep";

    private final JobRepository jobRepository;
    private final PlatformTransactionManager domainTransactionManager;

    public TechRoleStatisticsJobConfig(JobRepository jobRepository, @Qualifier(DOMAIN_TRANSACTION_MANAGER) PlatformTransactionManager domainTransactionManager) {
        this.jobRepository = jobRepository;
        this.domainTransactionManager = domainTransactionManager;
    }

    @Bean(name = TECH_ROLE_STATISTICS_JOB)
    public Job techRoleStatisticsJob(@Qualifier(TECH_ROLE_STATISTICS_STEP) Step techRoleStatisticsStep) {
        return new JobBuilder(TECH_ROLE_STATISTICS_JOB, jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(techRoleStatisticsStep)
                .build();
    }

    @Bean(name = TECH_ROLE_STATISTICS_STEP)
    public Step techRoleStatisticsStep(
            @Qualifier(TECH_PREFERENCE_TEST_RESULT_READER) MongoPagingItemReader<TechPreferenceTestResult> techPreferenceTestResultReader,
            @Qualifier(TECH_ROLE_STATISTICS_PROCESSOR) ItemProcessor<TechPreferenceTestResult, TechRoleStatistics> techRoleStatisticsProcessor,
            @Qualifier(TECH_ROLE_STATISTICS_WRITER) ItemWriter<TechRoleStatistics> techRoleStatisticsWriter) {
        return new StepBuilder(TECH_ROLE_STATISTICS_STEP, jobRepository)
                .<TechPreferenceTestResult, TechRoleStatistics>chunk(10, domainTransactionManager)
                .reader(techPreferenceTestResultReader)
                .processor(techRoleStatisticsProcessor)
                .writer(techRoleStatisticsWriter)
                .build();
    }
}
