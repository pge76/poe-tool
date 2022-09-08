package de.pge.poetoolbackend;

import com.mongodb.reactivestreams.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
public class ReactiveMongoConfig {
    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate(@Autowired MongoClient mongoClient) {
        return new ReactiveMongoTemplate(mongoClient, "test");
    }
}
