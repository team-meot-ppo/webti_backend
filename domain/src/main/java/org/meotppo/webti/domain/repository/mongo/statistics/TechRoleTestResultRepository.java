package org.meotppo.webti.domain.repository.mongo.statistics;

import org.meotppo.webti.domain.entity.mongo.statistics.TechRoleTestResult;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TechRoleTestResultRepository extends MongoRepository<TechRoleTestResult, String> {
}
