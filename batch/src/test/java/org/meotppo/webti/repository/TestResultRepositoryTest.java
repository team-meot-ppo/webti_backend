package org.meotppo.webti.repository;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.meotppo.webti.fixture.TestResultFixture.createTestResult;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.meotppo.webti.config.MongoTest;
import org.meotppo.webti.domain.entity.mongo.result.TestResult;
import org.meotppo.webti.domain.entity.type.MbtiType;
import org.meotppo.webti.domain.repository.mongo.testresult.TestResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@MongoTest
@SpringBootTest
@ActiveProfiles(value = "test")
public class TestResultRepositoryTest {

    @Autowired
    private TestResultRepository testResultRepository;

    @BeforeEach
    void setUp() {
        testResultRepository.deleteAll();
    }

    @Test
    void testSave() {
        // given
        TestResult result = createTestResult(MbtiType.INFJ, true);

        // when
        testResultRepository.save(result);
        TestResult foundResult = testResultRepository.findById(result.getId()).orElseThrow();

        // then
        assertSoftly(softly -> {
            softly.assertThat(foundResult.getId()).isEqualTo(result.getId());
            softly.assertThat(foundResult.getMbtiType()).isEqualTo(result.getMbtiType());
            softly.assertThat(foundResult.isMatch()).isEqualTo(result.isMatch());
        });
    }

    @Test
    void testDeleteAll() {
        // given
        TestResult result1 = createTestResult(MbtiType.INFJ, true);
        TestResult result2 = createTestResult(MbtiType.ENTP, false);

        testResultRepository.save(result1);
        testResultRepository.save(result2);

        assertSoftly(softly -> {
            // when
            softly.assertThat(testResultRepository.findAll()).hasSize(2);
            testResultRepository.deleteAll();

            // then
            softly.assertThat(testResultRepository.findAll()).isEmpty();
        });
    }
}
