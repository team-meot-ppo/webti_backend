package org.meotppo.webti.job;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.meotppo.webti.fixture.ProfileFixture.createProfile;
import static org.meotppo.webti.fixture.StatisticFixture.createStatistic;
import static org.meotppo.webti.fixture.TestResultFixture.createTestResult;
import static org.meotppo.webti.job.StatisticJobConfig.STATISTIC_JOB_NEW;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.meotppo.webti.domain.entity.jpa.profile.Profile;
import org.meotppo.webti.domain.entity.jpa.result.Statistic;
import org.meotppo.webti.domain.entity.mongo.result.TestResult;
import org.meotppo.webti.domain.entity.type.MbtiType;
import org.meotppo.webti.domain.repository.jpa.developertype.ProfileRepository;
import org.meotppo.webti.domain.repository.jpa.statistic.StatisticRepository;
import org.meotppo.webti.domain.repository.mongo.testresult.TestResultRepository;
import org.meotppo.webti.job.config.MongoTest;
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
    @Qualifier(STATISTIC_JOB_NEW)
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
        Profile profile1 = createProfile(MbtiType.INFJ, "Developer 1",
                "Description for Developer 1", "https://example.com/image1.png");
        Profile profile2 = createProfile(MbtiType.ENTP, "Developer 2",
                "Description for Developer 2", "https://example.com/image2.png");

        profileRepository.saveAll(List.of(profile1, profile2));

        Statistic statistic1 = createStatistic(profile1, 0L, 0L);
        Statistic statistic2 = createStatistic(profile2, 0L, 0L);

        statisticRepository.saveAll(List.of(statistic1, statistic2));

        List<TestResult> testResults = List.of(
                createTestResult(MbtiType.INFJ, true),
                createTestResult(MbtiType.INFJ, true),
                createTestResult(MbtiType.INFJ, false),
                createTestResult(MbtiType.ENTP, true),
                createTestResult(MbtiType.ENTP, false),
                createTestResult(MbtiType.ENTP, false)
        );

        testResultRepository.saveAll(testResults);

        // when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        Statistic updatedStatistic1 = statisticRepository.findByProfile(
                profileRepository.findByMbtiType(MbtiType.INFJ).get()).get();
        Statistic updatedStatistic2 = statisticRepository.findByProfile(
                profileRepository.findByMbtiType(MbtiType.ENTP).get()).get();

        // then
        assertSoftly(softly -> {
            softly.assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
            softly.assertThat(testResultRepository.findAll()).isEmpty();
            softly.assertThat(updatedStatistic1.getCount()).isEqualTo(3L);
            softly.assertThat(updatedStatistic1.getMatchCount()).isEqualTo(2L);
            softly.assertThat(updatedStatistic2.getCount()).isEqualTo(3L);
            softly.assertThat(updatedStatistic2.getMatchCount()).isEqualTo(1L);
        });
    }
}
