package org.meotppo.webti.job.reader;

import org.meotppo.webti.domain.entity.mongo.statistics.TechPreferenceTestResult;
import org.springframework.batch.item.data.MongoPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDateTime;
import java.util.Collections;

@Configuration
public class ReaderConfig {

    public static final String TECH_PREFERENCE_TEST_RESULT_READER = "techPreferenceTestResultReader";

    @Bean(name = TECH_PREFERENCE_TEST_RESULT_READER)
    public MongoPagingItemReader<TechPreferenceTestResult> techPreferenceTestResultReader(MongoTemplate mongoTemplate) {
        MongoPagingItemReader<TechPreferenceTestResult> reader = new MongoPagingItemReader<>();

        Query query = new Query();
        query.addCriteria(Criteria.where("createdAt").gt(LocalDateTime.now().minusHours(1)));

        reader.setTemplate(mongoTemplate);
        reader.setPageSize(10);
        reader.setQuery(query);
        reader.setSort(Collections.singletonMap("createdAt", Sort.Direction.ASC));
        reader.setTargetType(TechPreferenceTestResult.class);
        return reader;
    }
}
