package org.meotppo.webti.job.tasklet;

import org.bson.Document;
import org.meotppo.webti.domain.entity.jpa.developerProfile.WebDeveloperProfile;
import org.meotppo.webti.domain.entity.jpa.statistics.Statistic;
import org.meotppo.webti.domain.entity.type.MbtiType;
import org.meotppo.webti.domain.repository.jpa.developerType.WebDeveloperProfileRepository;
import org.meotppo.webti.domain.repository.jpa.statistics.StatisticRepository;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ConditionalOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Component
public class StatisticTasklet implements Tasklet {

    private final MongoTemplate mongoTemplate;
    private final StatisticRepository statisticRepository;
    private final WebDeveloperProfileRepository profileRepository;

    public StatisticTasklet(MongoTemplate mongoTemplate, StatisticRepository statisticRepository, WebDeveloperProfileRepository profileRepository) {
        this.mongoTemplate = mongoTemplate;
        this.statisticRepository = statisticRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        Aggregation aggregation = newAggregation(
                match(Criteria.where("createdAt").gt(LocalDateTime.now().minusHours(1))),
                group("mbtiType")
                        .count().as("count")
                        .sum(ConditionalOperators.when(Criteria.where("match").is(true)).then(1).otherwise(0)).as("matchCount")
        );
        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "test_result", Document.class);

        List<Document> documents = results.getMappedResults();
        documents.forEach(this::processDocument);
        return RepeatStatus.FINISHED;
    }

    private void processDocument(Document result) {
        String mbtiTypeString = result.getString("_id");
        MbtiType mbtiType = MbtiType.valueOf(mbtiTypeString);
        Long count = getLongValue(result, "count");
        Long matchCount = getLongValue(result, "matchCount");
        WebDeveloperProfile profile = profileRepository.findByMbtiType(mbtiType).orElseThrow(() -> new IllegalArgumentException("No profile found for type: " + mbtiType));

        Statistic existing = statisticRepository.findByDeveloperProfile(profile)
                .orElseGet(() -> new Statistic(profile, 0L, 0L));
        existing.updateCount(existing.getCount() + count);
        existing.updateMatchCount(existing.getMatchCount() + matchCount);
    }

    private Long getLongValue(Document document, String key) {
        return document.get(key, Number.class).longValue();
    }
}
