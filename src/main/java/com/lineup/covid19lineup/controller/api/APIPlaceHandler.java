package com.lineup.covid19lineup.controller.api;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.function.ServerResponse.*;

@Component
public class APIPlaceHandler {

    public ServerResponse getPlaces(ServerRequest request) {
        return ok().body(List.of("place1", "plac2"));
    }

    public ServerResponse createPlace(ServerRequest request) {
        return created(URI.create("/api/places/1")).body(true); // TODO: 1은 구현할 때 제대로 넣어주자
    }

    public ServerResponse getPPlace(ServerRequest request) {
        return ok().body("place " + request.pathVariable("placeId"));
    }

    public ServerResponse modifyPlace(ServerRequest request) {
        return ok().body(true);
    }

    public ServerResponse removePlace(ServerRequest request) {
        return ok().body(true);
    }
}
