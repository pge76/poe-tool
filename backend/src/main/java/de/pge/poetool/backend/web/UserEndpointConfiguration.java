package de.pge.poetool.backend.web;

import de.pge.poetool.backend.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserEndpointConfiguration {
    private static RequestPredicate i(RequestPredicate target) {
        return new CaseInsensitiveRequestPredicate(target);
    }

    @Bean
    RouterFunction<ServerResponse> routes(UserHandler userHandler) {
        return route(i(GET("/users")), userHandler::all);
    }
}
