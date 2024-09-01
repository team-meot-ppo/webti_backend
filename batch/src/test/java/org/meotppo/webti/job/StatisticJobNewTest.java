package org.meotppo.webti.job;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.meotppo.webti.config.MongoTest;
import org.meotppo.webti.domain.entity.jpa.file.Image;
import org.meotppo.webti.domain.entity.jpa.profile.Profile;
import org.meotppo.webti.domain.entity.jpa.result.Statistic;
import org.meotppo.webti.domain.entity.mongo.result.TestResult;
import org.meotppo.webti.domain.entity.type.MbtiType;
import org.meotppo.webti.domain.repository.jpa.developertype.ProfileRepository;
import org.meotppo.webti.domain.repository.jpa.statistic.StatisticRepository;
import org.meotppo.webti.domain.repository.mongo.testresult.TestResultRepository;
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

        Image image1 = Image.builder()
                .url("https://example.com/image1.png")
                .build();

        Image image2 = Image.builder()
                .url("https://example.com/image2.png")
                .build();

        Profile profile1 = Profile.builder()
                .mbtiType(MbtiType.INFJ)
                .result("Developer 1")
                .description("Description for Developer 1")
                .image(image1)
                .build();

        Profile profile2 = Profile.builder()
                .mbtiType(MbtiType.ENTP)
                .result("Developer 2")
                .description("Description for Developer 2")
                .image(image2)
                .build();

        profileRepository.saveAll(Arrays.asList(profile1, profile2));

        Statistic statistic1 = Statistic.builder()
                .profile(profile1)
                .count(0L)
                .matchCount(0L)
                .build();

        Statistic statistic2 = Statistic.builder()
                .profile(profile2)
                .count(0L)
                .matchCount(0L)
                .build();

        statisticRepository.saveAll(Arrays.asList(statistic1, statistic2));

        TestResult testResult1 = TestResult.builder()
                .mbtiType(MbtiType.INFJ)
                .match(true)
                .build();

        TestResult testResult2 = TestResult.builder()
                .mbtiType(MbtiType.ENTP)
                .match(false)
                .build();

        testResultRepository.saveAll(Arrays.asList(testResult1, testResult2));
    }

    @Test
    public void testStatisticJobNew() throws Exception {
        // given
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        // when
        Statistic updatedStatistic1 = statisticRepository.findByProfile(
                profileRepository.findByMbtiType(MbtiType.INFJ).orElseThrow()).orElseThrow();
        Statistic updatedStatistic2 = statisticRepository.findByProfile(
                profileRepository.findByMbtiType(MbtiType.ENTP).orElseThrow()).orElseThrow();

        // then
        assertSoftly(softly -> {
            assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
            softly.assertThat(updatedStatistic1.getCount()).isEqualTo(1L);
            softly.assertThat(updatedStatistic1.getMatchCount()).isEqualTo(1L);
            softly.assertThat(updatedStatistic2.getCount()).isEqualTo(1L);
            softly.assertThat(updatedStatistic2.getMatchCount()).isEqualTo(0L);
        });
    }
}
