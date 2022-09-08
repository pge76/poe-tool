package de.pge.poetoolbackend;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface SampleReactiveRepository extends ReactiveMongoRepository<SampleEntity, String> {
    Mono<SampleEntity> findAllByOwner(String owner);
}
