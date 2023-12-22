package com.workshop.sport.client;

import com.workshop.sport.model.Sport;
import com.workshop.sport.repository.SportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
@Slf4j
@RequiredArgsConstructor
public class SportClient {

    private final WebClient webClient;
    private final SportRepository sportRepository;

    public Flux<Sport> searchSport() {
        return webClient.get().uri(uri->uri.path("/v3/c9eef922-f377-425a-a22c-bf4fadfa0a04").build())
                .exchangeToFlux(response -> response.bodyToFlux(Sport.class))
                .limitRate(20)
                .doOnError(e -> log.error("Error: {}", e.getMessage()))
                .flatMap(sportRepository::save)
                .doOnRequest(n -> log.info("Requested: {}", n))
                .doOnNext(sport -> log.info("Processing: {}", sport.getName()));
    }
}
