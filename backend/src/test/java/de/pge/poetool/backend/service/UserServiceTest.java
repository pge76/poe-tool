package de.pge.poetool.backend.service;

import de.pge.poetool.backend.data.UserDataInitializer;
import de.pge.poetool.backend.entity.dto.UserDto;
import de.pge.poetool.backend.service.impl.UserServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Log4j2
@DataMongoTest
@Import({UserServiceImpl.class, UserDataInitializer.class})
public class UserServiceTest {
    private final UserService userService;

    public UserServiceTest(@Autowired UserService userService) {
        this.userService = userService;
    }


    @Test
    public void getAll() {
        Flux<UserDto> users = userService.all();
        StepVerifier
                .create(users)
                .expectNextMatches(user -> user.getFirstName().equals("Robert"))
                .expectNextMatches(user -> user.getFirstName().equals("Tywin"))
                .verifyComplete();
    }

    @Test
    public void getUser() {
        Mono<UserDto> user = userService.getUser("robert.baratheon@baratheon.com");
        StepVerifier.create(user)
                .expectNextCount(1)
                .verifyComplete();
    }

}
