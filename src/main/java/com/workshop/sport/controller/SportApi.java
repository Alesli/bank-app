package com.workshop.sport.controller;

import com.workshop.sport.client.SportClient;
import com.workshop.sport.model.Sport;
import com.workshop.sport.repository.SportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SportApi {
    private final SportRepository sportRepository;
    private final SportClient sportClient;

    public Mono<ServerResponse> createSport(ServerRequest request) {
        var sportName = request.pathVariable("sportname");
        return sportRepository.findByName(sportName)
                .onErrorResume(e -> Mono.empty())
                .flatMap(sport -> ServerResponse.status(HttpStatus.CONFLICT).build())
                .switchIfEmpty(Mono.defer(() -> {
                    var newSport = new Sport();
                    newSport.setName(sportName);
                    return sportRepository.save(newSport)
                            .flatMap(savedSport -> ServerResponse.status(HttpStatus.CREATED).bodyValue(savedSport));
                }))
                .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    public Mono<ServerResponse> searchSport(ServerRequest request) {
        var query = request.queryParam("name").orElse("");
        return sportRepository.findByName(query)
                .flatMap(sport -> ServerResponse.ok().bodyValue(sport))
                .switchIfEmpty(ServerResponse.notFound().build())
                .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    public Mono<ServerResponse> getListSports(ServerRequest request) {
        return sportRepository.findAll()
                .collectList()
                .flatMap(sports -> ServerResponse.ok().bodyValue(sports))
                .switchIfEmpty(ServerResponse.notFound().build())
                .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
