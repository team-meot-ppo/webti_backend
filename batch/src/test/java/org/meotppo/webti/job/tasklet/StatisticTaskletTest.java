package org.meotppo.webti.job.tasklet;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.meotppo.webti.fixture.ProfileFixture.createProfile;
import static org.meotppo.webti.fixture.StatisticFixture.createStatistic;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.batch.test.MetaDataInstanceFactory.createStepExecution;

import java.util.Collections;
import java.util.Optional;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.meotppo.webti.domain.entity.jpa.profile.Profile;
import org.meotppo.webti.domain.entity.jpa.result.Statistic;
import org.meotppo.webti.domain.entity.type.MbtiType;
import org.meotppo.webti.domain.repository.jpa.developertype.ProfileRepository;
import org.meotppo.webti.domain.repository.jpa.statistic.StatisticRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

@ExtendWith(MockitoExtension.class)
class StatisticTaskletTest {

    @InjectMocks
    private StatisticTasklet tasklet;

    @Mock
    private MongoTemplate mongoTemplate;

    @Mock
    private StatisticRepository statisticRepository;

    @Mock
    private ProfileRepository profileRepository;

    @Test
    void testStatisticTasklet() throws Exception {
        // given
        Document document = new Document("_id", "INFJ").append("count", 5L).append("matchCount", 3L);
        AggregationResults<Document> results =
                new AggregationResults<>(Collections.singletonList(document), new Document());

        given(mongoTemplate.aggregate(any(Aggregation.class), eq("test_result"), eq(Document.class)))
                .willReturn(results);

        Profile profile = createProfile(MbtiType.INFJ, "Developer 1",
                "Description for Developer 1", "https://example.com/image1.png");
        Statistic statistic = createStatistic(profile, 0L, 0L);

        given(profileRepository.findByMbtiType(MbtiType.INFJ))
                .willReturn(Optional.of(profile));
        given(statisticRepository.findByProfile(profile))
                .willReturn(Optional.of(statistic));

        StepExecution stepExecution = createStepExecution();
        ChunkContext chunkContext = new ChunkContext(new StepContext(stepExecution));
        StepContribution contribution = new StepContribution(stepExecution);

        // when
        RepeatStatus status = tasklet.execute(contribution, chunkContext);

        // then
        assertSoftly(softly -> {
            softly.assertThat(statistic.getCount()).isEqualTo(5L);
            softly.assertThat(statistic.getMatchCount()).isEqualTo(3L);
            softly.assertThat(status).isEqualTo(RepeatStatus.FINISHED);
        });
    }
}
