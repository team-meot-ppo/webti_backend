package org.meotppo.webti.job;

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

import static org.meotppo.webti.config.TransactionManagerConfig.DOMAIN_TRANSACTION_MANAGER;

@Configuration
public class StatisticJobConfig {

    public static final String STATISTIC_JOB = "statisticJob";
    public static final String STATISTIC_STEP = "statisticStep";

    private final JobRepository jobRepository;
    private final PlatformTransactionManager domainTransactionManager;

    public StatisticJobConfig(JobRepository jobRepository, @Qualifier(DOMAIN_TRANSACTION_MANAGER) PlatformTransactionManager domainTransactionManager) {
        this.jobRepository = jobRepository;
        this.domainTransactionManager = domainTransactionManager;
    }

//    @Bean(name = STATISTIC_JOB)  // 기존 Job
//    public Job statisticJob(@Qualifier(STATISTIC_STEP) Step statisticStep) {
//        return new JobBuilder(STATISTIC_JOB, jobRepository)
//                .incrementer(new RunIdIncrementer())
//                .start(statisticStep)
//                .build();
//    }
//
//    @Bean(name = STATISTIC_STEP)
//    public Step statisticStep(
//            @Qualifier(TEST_RESULT_READER) MongoPagingItemReader<TestResult> testResultReader,
//            @Qualifier(STATISTIC_PROCESSOR) ItemProcessor<TestResult, Statistic> statisticProcessor,
//            @Qualifier(STATISTIC_WRITER) ItemWriter<Statistic> statisticWriter) {
//        return new StepBuilder(STATISTIC_STEP, jobRepository)
//                .<TestResult, Statistic>chunk(10, domainTransactionManager)
//                .reader(testResultReader)
//                .processor(statisticProcessor)
//                .writer(statisticWriter)
//                .build();
//    }

    @Bean(name = STATISTIC_JOB)  // 개선 Job
    public Job statisticJob(@Qualifier(STATISTIC_STEP) Step statisticStep) {
        return new JobBuilder(STATISTIC_JOB, jobRepository)
                .start(statisticStep)
                .build();
    }

    @Bean(name = STATISTIC_STEP)
    public Step statisticStep(StatisticTasklet statisticTasklet) {
        return new StepBuilder(STATISTIC_STEP, jobRepository)
                .tasklet(statisticTasklet, domainTransactionManager)
                .build();
    }
}
