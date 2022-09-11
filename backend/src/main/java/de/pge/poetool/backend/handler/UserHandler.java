package de.pge.poetool.backend.handler;

import de.pge.poetool.backend.entity.dto.UserDto;
import de.pge.poetool.backend.service.UserService;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class UserHandler {

    private final UserService userService;

    UserHandler(UserService userService) {
        this.userService = userService;
    }

    private static Mono<ServerResponse> defaultWriteResponse(Publisher<UserDto> users) {
        return Mono
                .from(users)
                .flatMap(user -> ServerResponse
                        .created(URI.create("users" + user.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .build()
                );
    }

    private static Mono<ServerResponse> defaultReadResponse(Publisher<UserDto> users) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(users, UserDto.class);
    }


    public Mono<ServerResponse> all(ServerRequest ignoredRequest) {
        return defaultReadResponse(this.userService.all());
    }
}
