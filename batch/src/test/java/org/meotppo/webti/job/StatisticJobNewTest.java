package org.meotppo.webti.job;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.meotppo.webti.config.MongoTest;
import org.meotppo.webti.domain.entity.jpa.profile.Profile;
import org.meotppo.webti.domain.entity.jpa.result.Statistic;
import org.meotppo.webti.domain.entity.mongo.result.TestResult;
import org.meotppo.webti.domain.entity.type.MbtiType;
import org.meotppo.webti.domain.repository.jpa.developertype.ProfileRepository;
import org.meotppo.webti.domain.repository.jpa.statistic.StatisticRepository;
import org.meotppo.webti.domain.repository.mongo.testresult.TestResultRepository;
import org.meotppo.webti.fixture.ProfileFixture;
import org.meotppo.webti.fixture.StatisticFixture;
import org.meotppo.webti.fixture.TestResultFixture;
import org.meotppo.webti.util.DatabaseUtils;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@MongoTest
@SpringBatchTest
@SpringBootTest
@ActiveProfiles("test")
public class StatisticJobNewTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    @Qualifier(StatisticJobConfig.STATISTIC_JOB_NEW)
    private Job statisticJobNew;

    @Autowired
    private TestResultRepository testResultRepository;

    @Autowired
    private StatisticRepository statisticRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private DatabaseUtils databaseUtils;

    @BeforeEach
    public void setUp() {
        testResultRepository.deleteAll();
        databaseUtils.clean();

        jobLauncherTestUtils.setJob(statisticJobNew);
    }

    @Test
    public void testStatisticJobNew() throws Exception {
        // given
        Profile profile1 = ProfileFixture.createProfile(MbtiType.INFJ, "Developer 1", "Description for Developer 1",
                "https://example.com/image1.png");
        Profile profile2 = ProfileFixture.createProfile(MbtiType.ENTP, "Developer 2", "Description for Developer 2",
                "https://example.com/image2.png");

        profileRepository.saveAll(List.of(profile1, profile2));

        Statistic statistic1 = StatisticFixture.createStatistic(profile1, 0L, 0L);
        Statistic statistic2 = StatisticFixture.createStatistic(profile2, 0L, 0L);

        statisticRepository.saveAll(List.of(statistic1, statistic2));

        List<TestResult> testResults = List.of(
                TestResultFixture.createTestResult(MbtiType.INFJ, true),
                TestResultFixture.createTestResult(MbtiType.INFJ, true),
                TestResultFixture.createTestResult(MbtiType.INFJ, false),
                TestResultFixture.createTestResult(MbtiType.ENTP, true),
                TestResultFixture.createTestResult(MbtiType.ENTP, false),
                TestResultFixture.createTestResult(MbtiType.ENTP, false)
        );

        testResultRepository.saveAll(testResults);

        // when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        // then
        Statistic updatedStatistic1 = statisticRepository.findByProfile(
                profileRepository.findByMbtiType(MbtiType.INFJ).get()).get();
        Statistic updatedStatistic2 = statisticRepository.findByProfile(
                profileRepository.findByMbtiType(MbtiType.ENTP).get()).get();

        assertSoftly(softly -> {
            assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
            softly.assertThat(updatedStatistic1.getCount()).isEqualTo(3L);
            softly.assertThat(updatedStatistic1.getMatchCount()).isEqualTo(2L);
            softly.assertThat(updatedStatistic2.getCount()).isEqualTo(3L);
            softly.assertThat(updatedStatistic2.getMatchCount()).isEqualTo(1L);
        });
    }
}
