package org.meotppo.webti.config;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "org.meotppo.webti.domain.repository.jpa")
public class JpaConfig {
}
