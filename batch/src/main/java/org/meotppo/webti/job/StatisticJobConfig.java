package org.meotppo.webti.job;

import org.meotppo.webti.domain.entity.jpa.statistics.Statistic;
import org.meotppo.webti.domain.entity.mongo.testresult.TestResult;
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
import static org.meotppo.webti.job.processor.ProcessorConfig.STATISTIC_PROCESSOR;
import static org.meotppo.webti.job.reader.ReaderConfig.TEST_RESULT_READER;
import static org.meotppo.webti.job.writer.WriterConfig.STATISTIC_WRITER;

@Configuration
public class StatisticJobConfig {  // TODO. 통계 로직의 성능 개선

    public static final String STATISTIC_JOB = "statisticJob";
    public static final String STATISTIC_STEP = "statisticStep";

    private final JobRepository jobRepository;
    private final PlatformTransactionManager domainTransactionManager;

    public StatisticJobConfig(JobRepository jobRepository, @Qualifier(DOMAIN_TRANSACTION_MANAGER) PlatformTransactionManager domainTransactionManager) {
        this.jobRepository = jobRepository;
        this.domainTransactionManager = domainTransactionManager;
    }

    @Bean(name = STATISTIC_JOB)
    public Job statisticJob(@Qualifier(STATISTIC_STEP) Step statisticStep) {
        return new JobBuilder(STATISTIC_JOB, jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(statisticStep)
                .build();
    }

    @Bean(name = STATISTIC_STEP)
    public Step statisticStep(
            @Qualifier(TEST_RESULT_READER) MongoPagingItemReader<TestResult> testResultReader,
            @Qualifier(STATISTIC_PROCESSOR) ItemProcessor<TestResult, Statistic> statisticProcessor,
            @Qualifier(STATISTIC_WRITER) ItemWriter<Statistic> statisticWriter) {
        return new StepBuilder(STATISTIC_STEP, jobRepository)
                .<TestResult, Statistic>chunk(10, domainTransactionManager)
                .reader(testResultReader)
                .processor(statisticProcessor)
                .writer(statisticWriter)
                .build();
    }
}
