package de.pge.poetool.backend.sample;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class ReactiveConceptsTest {

    /*
     * do not use this method of testing Publishers
     */
    @Test
    void testFluxStringWithAsserts() {
        Flux<String> fluxOfString = Flux.just("Hello", "World", "!").map(s -> s.concat(" flux"));
        List<String> expectedResult = Arrays.asList("Hello flux", "World flux", "! flux");
        assertThat(fluxOfString.collectList().block()).isEqualTo(expectedResult);
    }

    /*
     * use this method
     */
    @Test
    void testFluxStringWithStepVerifier() {
        Flux<String> fluxOfString = Flux.just("Hello", "World", "!").map(s -> s.concat(" flux"));
        StepVerifier.create(fluxOfString)
                .expectNext("Hello flux", "World flux", "! flux")
                .verifyComplete();

    }
}
