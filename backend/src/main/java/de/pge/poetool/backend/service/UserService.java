package de.pge.poetool.backend.service;

import de.pge.poetool.backend.entity.dto.UserDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserDto> getUser(String email);
    Flux<UserDto> all();
}
