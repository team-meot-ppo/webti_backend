package org.meotppo.webti.domain.repository.mongo.statistics;

import org.meotppo.webti.domain.entity.mongo.statistics.TechPreferenceTestResult;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TechPreferenceTestResultRepository extends MongoRepository<TechPreferenceTestResult, String> {
}
