package com.workshop.sport.config;

import com.workshop.sport.client.SportClient;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import reactor.core.publisher.Mono;

@Configuration
public class R2dbcConfig {

    @Bean
    public R2dbcEntityTemplate r2dbcEntityTemplate(ConnectionFactory connectionFactory) {
        return new R2dbcEntityTemplate(connectionFactory);
    }

    @Bean
    CommandLineRunner init(SportClient sportClient, R2dbcEntityTemplate entityTemplate) {
        return args -> {
            Mono<Void> createTableMono = entityTemplate.getDatabaseClient().sql(
                            "CREATE TABLE IF NOT EXISTS SPORT (ID SERIAL PRIMARY KEY, NAME VARCHAR(255) NOT NULL)")
                    .fetch()
                    .rowsUpdated()
                    .then();

            Mono<Void> etlMono = sportClient.searchSport().then();

            createTableMono
                    .then(etlMono)
                    .subscribe();
        };
    }
}

