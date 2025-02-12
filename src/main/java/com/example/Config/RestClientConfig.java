package com.example.Config;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.eclipse.microprofile.rest.client.RestClientBuilder;

import com.example.Services.DigimonRestClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class RestClientConfig {
        private static final String DIGIMON_API_URL = "https://digi-api.com/api/v1/digimon";
    private static final String DRAGONBALL_API_URL = "https://dragonball-api.com/api/characters";

    @Produces
    @ApplicationScoped
    public DigimonRestClient createDigimonRestClient() {
        return RestClientBuilder.newBuilder()
                .baseUri(URI.create(DIGIMON_API_URL))
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build(DigimonRestClient.class);
    }

    @Produces
    @ApplicationScoped
    public DragonBallRestClient createDragonBallRestClient() {
        return RestClientBuilder.newBuilder()
                .baseUri(URI.create(DRAGONBALL_API_URL))
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build(DragonBallRestClient.class);
    }
}
