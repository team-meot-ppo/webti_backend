package org.meotppo.webti.domain.config.mongo;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Configuration
public class MongoConfig {

    @Bean
    public MongoCustomConversions customConversions(
            LocalDateTimeToDateKstConverter localDateTimeToDateKstConverter,
            DateToLocalDateTimeKstConverter dateToLocalDateTimeKstConverter) {
        return new MongoCustomConversions(List.of(localDateTimeToDateKstConverter, dateToLocalDateTimeKstConverter));
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDbFactory, MongoMappingContext context,
                                       MongoCustomConversions customConversions) {
        MappingMongoConverter converter = new MappingMongoConverter(mongoDbFactory, context);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        converter.setCustomConversions(customConversions);
        converter.afterPropertiesSet();
        return new MongoTemplate(mongoDbFactory, converter);
    }
}
