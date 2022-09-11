package de.pge.poetool.backend.web;

import org.springframework.http.server.RequestPath;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.support.ServerRequestWrapper;

import java.net.URI;

public class CaseInsensitiveRequestPredicate implements RequestPredicate {
    private final RequestPredicate target;

    public CaseInsensitiveRequestPredicate(RequestPredicate target) {
        this.target = target;
    }

    public boolean test(ServerRequest request) {
        return this.target.test(new LowerCaseUriServerRequestWrapper(request));
    }

    public String toString() {
        return this.target.toString();
    }

    private static class LowerCaseUriServerRequestWrapper extends ServerRequestWrapper {
        public LowerCaseUriServerRequestWrapper(ServerRequest request) {
            super(request);
        }

        public URI uri() {
            return URI.create(super.uri().toString().toLowerCase());
        }

        @Override
        public String path() {
            return uri().getRawPath();
        }

        @Override
        public RequestPath requestPath() {
            // Not sure if this is the correct way, to create the requestPath
            return RequestPath.parse(uri(), "");
        }
    }
}
