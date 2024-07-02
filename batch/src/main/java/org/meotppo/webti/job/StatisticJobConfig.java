package org.meotppo.webti.job;

import org.meotppo.webti.domain.entity.jpa.statistics.Statistic;
import org.meotppo.webti.domain.entity.mongo.testresult.TestResult;
import org.meotppo.webti.job.tasklet.StatisticTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoPagingItemReader;

import static org.meotppo.webti.config.TransactionManagerConfig.DOMAIN_TRANSACTION_MANAGER;

@Configuration
public class StatisticJobConfig {

    public static final String STATISTIC_JOB_OLD = "statisticJobOld";
    public static final String STATISTIC_STEP_OLD = "statisticStepOld";
    public static final String STATISTIC_JOB_NEW = "statisticJobNew";
    public static final String STATISTIC_STEP_NEW = "statisticStepNew";

    private final JobRepository jobRepository;
    private final PlatformTransactionManager domainTransactionManager;

    public StatisticJobConfig(JobRepository jobRepository, @Qualifier(DOMAIN_TRANSACTION_MANAGER) PlatformTransactionManager domainTransactionManager) {
        this.jobRepository = jobRepository;
        this.domainTransactionManager = domainTransactionManager;
    }

    @Bean(name = STATISTIC_JOB_OLD)
    public Job statisticJobOld(@Qualifier(STATISTIC_STEP_OLD) Step statisticStepOld) {
        return new JobBuilder(STATISTIC_JOB_OLD, jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(statisticStepOld)
                .build();
    }

    @Bean(name = STATISTIC_STEP_OLD)
    public Step statisticStepOld(
            @Qualifier("testResultReader") MongoPagingItemReader<TestResult> testResultReader,
            @Qualifier("statisticProcessor") ItemProcessor<TestResult, Statistic> statisticProcessor,
            @Qualifier("statisticWriter") ItemWriter<Statistic> statisticWriter) {
        return new StepBuilder(STATISTIC_STEP_OLD, jobRepository)
                .<TestResult, Statistic>chunk(10, domainTransactionManager)
                .reader(testResultReader)
                .processor(statisticProcessor)
                .writer(statisticWriter)
                .build();
    }

    @Bean(name = STATISTIC_JOB_NEW)
    public Job statisticJobNew(@Qualifier(STATISTIC_STEP_NEW) Step statisticStepNew) {
        return new JobBuilder(STATISTIC_JOB_NEW, jobRepository)
                .start(statisticStepNew)
                .build();
    }

    @Bean(name = STATISTIC_STEP_NEW)
    public Step statisticStepNew(StatisticTasklet statisticTasklet) {
        return new StepBuilder(STATISTIC_STEP_NEW, jobRepository)
                .tasklet(statisticTasklet, domainTransactionManager)
                .build();
    }
}
