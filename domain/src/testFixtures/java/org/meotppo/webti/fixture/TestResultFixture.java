package org.meotppo.webti.fixture;

import org.meotppo.webti.domain.entity.mongo.result.TestResult;
import org.meotppo.webti.domain.entity.type.MbtiType;

public class TestResultFixture {
    public static TestResult createTestResult(MbtiType mbtiType, boolean match) {
        return TestResult.builder()
                .mbtiType(mbtiType)
                .match(match)
                .build();
    }
}
