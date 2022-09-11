package de.pge.poetool.backend.data;

import de.pge.poetool.backend.entity.model.User;
import de.pge.poetool.backend.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Log4j2
@Component
@Profile("dev")
public class UserDataInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final UserRepository userRepository;

    public UserDataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        userRepository.deleteAll()
                .thenMany(
                        Flux.just(
                                User.builder()
                                        .email("robert.baratheon@baratheon.com")
                                        .firstName("Robert")
                                        .lastName("Baratheon")
                                        .password("drunkbastard")
                                        .build(),
                                User.builder()
                                        .email("tywin.lannister@casterlystein.com")
                                        .firstName("Tywin")
                                        .lastName("Lannister")
                                        .password("starksucks")
                                        .build()
                        ).flatMap(userRepository::save)
                )
                .thenMany(userRepository.findAll())
                .subscribe(user -> log.info("saved " + user.toString()));
    }
}
