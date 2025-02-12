package com.example.Config;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.vertx.mutiny.core.Vertx;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;


@ApplicationScoped
public class AppConfig {
    
    @Produces
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Produces
    public WebClient webClient(Vertx vertx) {
        return WebClient.builder().build();
    }
}
