package org.meotppo.webti.job.reader;

import org.meotppo.webti.domain.entity.mongo.statistics.TechPreferenceTestResult;
import org.springframework.batch.item.data.MongoPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.HashMap;

@Configuration
public class ReaderConfig {

    public static final String TECH_PREFERENCE_TEST_RESULT_READER = "techPreferenceTestResultReader";

    @Bean(name = TECH_PREFERENCE_TEST_RESULT_READER)
    public MongoPagingItemReader<TechPreferenceTestResult> techPreferenceTestResultReader(MongoTemplate mongoTemplate) {
        MongoPagingItemReader<TechPreferenceTestResult> reader = new MongoPagingItemReader<>();
        reader.setTemplate(mongoTemplate);
        reader.setPageSize(10);
        reader.setQuery(new Query());
        reader.setSort(new HashMap<String, Sort.Direction>() {{
            put("_id", Sort.Direction.ASC);
        }});
        reader.setTargetType(TechPreferenceTestResult.class);
        return reader;
    }
}
