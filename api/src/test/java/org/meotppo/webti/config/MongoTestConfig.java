package org.meotppo.webti.config;

import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

public class MongoTestConfig {

    @Bean(destroyMethod = "shutdown")
    public MongoServer mongoServer() {
        MongoServer server = new MongoServer(new MemoryBackend());
        server.bind();
        return server;
    }

    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory(MongoServer mongoServer) {
        String connectionString = mongoServer.bindAndGetConnectionString();
        return new SimpleMongoClientDatabaseFactory(connectionString + "/test");
    }
}
