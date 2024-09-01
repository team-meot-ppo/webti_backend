package org.meotppo.webti.config;

import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoAuditing
@EnableMongoRepositories(basePackages = "org.meotppo.webti.domain.repository.mongo")
public class MongoConfig {
}
