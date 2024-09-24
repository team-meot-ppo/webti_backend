package org.meotppo.webti.config;

import static org.meotppo.webti.config.TransactionManagerConfig.DOMAIN_ENTITY_MANAGER_FACTORY;
import static org.meotppo.webti.config.TransactionManagerConfig.DOMAIN_TRANSACTION_MANAGER;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(
        basePackages = "org.meotppo.webti.domain.repository.jpa",
        entityManagerFactoryRef = DOMAIN_ENTITY_MANAGER_FACTORY,
        transactionManagerRef = DOMAIN_TRANSACTION_MANAGER
)
public class JpaConfig {
}
