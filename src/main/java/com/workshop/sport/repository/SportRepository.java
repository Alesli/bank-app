package com.workshop.sport.repository;

import com.workshop.sport.model.Sport;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SportRepository extends ReactiveCrudRepository<Sport, Integer> {
        Mono<Sport> findByName(String name);
        Flux<Sport> findAllByNameContains(String name);
}
