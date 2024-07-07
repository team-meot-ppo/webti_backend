package org.meotppo.webti.job.tasklet;

import org.bson.Document;
import org.meotppo.webti.domain.entity.jpa.developerprofile.WebDeveloperProfile;
import org.meotppo.webti.domain.entity.jpa.statistics.Statistic;
import org.meotppo.webti.domain.entity.type.MbtiType;
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

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@Component
public class StatisticTasklet implements Tasklet {

    private final MongoTemplate mongoTemplate;
    private final StatisticRepository statisticRepository;
    private final org.meotppo.webti.domain.repository.jpa.developertype.WebDeveloperProfileRepository profileRepository;

    public StatisticTasklet(MongoTemplate mongoTemplate, StatisticRepository statisticRepository, org.meotppo.webti.domain.repository.jpa.developertype.WebDeveloperProfileRepository profileRepository) {
        this.mongoTemplate = mongoTemplate;
        this.statisticRepository = statisticRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        Aggregation aggregation = newAggregation(
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
        MbtiType mbtiType = getMbtiType(result, "_id").orElseThrow(() -> new IllegalArgumentException("MBTI type is missing or invalid"));
        Long count = getLongValue(result, "count").orElseThrow(() -> new IllegalArgumentException("Count is missing"));
        Long matchCount = getLongValue(result, "matchCount").orElseThrow(() -> new IllegalArgumentException("Match count is missing"));
        WebDeveloperProfile profile = profileRepository.findByMbtiType(mbtiType).orElseThrow(() -> new IllegalArgumentException("No profile found for type: " + mbtiType));

        Statistic existing = statisticRepository.findByDeveloperProfile(profile).orElseGet(() -> new Statistic(profile, 0L, 0L));
        existing.updateCount(existing.getCount() + count);
        existing.updateMatchCount(existing.getMatchCount() + matchCount);
    }

    private Optional<MbtiType> getMbtiType(Document document, String key) {
        return Optional.ofNullable(document.getString(key))
                .map(mbtiTypeString -> {
                    try {
                        return MbtiType.valueOf(mbtiTypeString);
                    } catch (IllegalArgumentException e) {
                        return null;
                    }
                });
    }

    private Optional<Long> getLongValue(Document document, String key) {
        return Optional.ofNullable(document.get(key, Number.class)).map(Number::longValue);
    }
}
