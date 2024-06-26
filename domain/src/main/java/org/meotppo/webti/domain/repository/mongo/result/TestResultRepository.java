package org.meotppo.webti.domain.repository.mongo.result;

import org.meotppo.webti.domain.entity.mongo.result.TestResult;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestResultRepository extends MongoRepository<TestResult, String> {
}
