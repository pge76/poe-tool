package de.pge.poetoolbackend;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DataMongoTest(excludeAutoConfiguration = org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration.class)
@ExtendWith(SpringExtension.class)
class PoeToolBackendApplicationTests {

    @Test
    void contextLoads() {
        assertEquals(2, 1 + 1); // just verify that the spring context loads without errors
    }

    @Test
    void shouldSaveSingleSampleEntity(@Autowired final SampleReactiveRepository repository) {

        SampleEntity sampleEntity = SampleEntity.builder()
                .owner("tywin")
                .id(null)
                .value(12.2)
                .build();

        Publisher<SampleEntity> setup = repository.deleteAll().then(repository.save(sampleEntity));
        StepVerifier
                .create(setup)
                .expectNextCount(1)
                .verifyComplete();
    }


    @Test
    void shouldSaveSampleEntity(@Autowired final SampleReactiveRepository repository) {
        String SAMPLE_NAME = "tywin";
        SampleEntity sampleEntity = SampleEntity.builder()
                .owner(SAMPLE_NAME)
                .id(null)
                .value(12.3)
                .build();

        Publisher<SampleEntity> setup = repository.deleteAll().then(repository.save(sampleEntity));
        Mono<SampleEntity> find = repository.findAllByOwner(SAMPLE_NAME);
        Publisher<SampleEntity> composite = Mono
                .from(setup)
                .then(find);
        StepVerifier
                .create(composite)
                .consumeNextWith(sample -> {
                    assertThat(sample.getId()).isNotNull();
                    assertThat(sample.getValue()).isEqualTo(12.3);
                    assertThat(sample.getOwner()).isEqualTo(SAMPLE_NAME);
                }).verifyComplete();

    }
}
