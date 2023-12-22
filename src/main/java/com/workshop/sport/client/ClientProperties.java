package com.workshop.sport.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "client")
public class ClientProperties {

    private String baseUrl;
}
