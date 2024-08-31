package org.meotppo.webti.config;

import static org.meotppo.webti.config.TransactionManagerConfig.META_TRANSACTION_MANAGER;

import javax.sql.DataSource;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.ExecutionContextSerializer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.dao.Jackson2ExecutionContextStringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.autoconfigure.batch.JobLauncherApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.StringUtils;

@Configuration
@EnableConfigurationProperties(BatchProperties.class)
public class BatchConfig extends DefaultBatchConfiguration {

    private final DataSource mainDataSource;
    private final PlatformTransactionManager metaTransactionManager;

    public BatchConfig(@Qualifier(DataSourceConfig.META_DATASOURCE) DataSource metaDataSource,
                       @Qualifier(META_TRANSACTION_MANAGER) PlatformTransactionManager metaTransactionManager) {
        this.mainDataSource = metaDataSource;
        this.metaTransactionManager = metaTransactionManager;
    }

    @Override
    protected ExecutionContextSerializer getExecutionContextSerializer() {
        return new Jackson2ExecutionContextStringSerializer();
    }

    @Override
    protected DataSource getDataSource() {
        return mainDataSource;
    }

    @Override
    protected PlatformTransactionManager getTransactionManager() {
        return metaTransactionManager;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "spring.batch.job", name = "enabled", havingValue = "true", matchIfMissing = true)
    public JobLauncherApplicationRunner jobLauncherApplicationRunner(JobLauncher jobLauncher, JobExplorer jobExplorer,
                                                                     JobRepository jobRepository,
                                                                     BatchProperties properties) {
        JobLauncherApplicationRunner runner = new JobLauncherApplicationRunner(jobLauncher, jobExplorer, jobRepository);
        String jobNames = properties.getJob().getName();
        if (StringUtils.hasText(jobNames)) {
            runner.setJobName(jobNames);
        }
        return runner;
    }
}
