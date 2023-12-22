package com.workshop.sport.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class SportRouter {

    @Bean
    public RouterFunction<ServerResponse> sportRoutes(SportApi api) {
        return route(POST("/api/v1/sport/{sportname}"), api::createSport)
                .andRoute(GET("/api/v1/sport"), api::searchSport)
                .andRoute(GET("/api/v1/sports"), api::getListSports);
    }
}
