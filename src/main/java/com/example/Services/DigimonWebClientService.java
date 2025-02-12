package com.example.Services;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.example.Models.DigimonResponse;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class DigimonWebClientService {
    
        @Inject
        @RestClient
        DigimonRestClient digimonRestClient;
        
        public Uni<List<DigimonResponse>> fetchDigimons() {
            List<String> digimons = List.of("leviamon", "mugendramon", "sakuyamon");
            
            List<CompletableFuture<DigimonResponse>> futures = digimons.stream()
                .map(name -> digimonRestClient.fetchDigimon(name)
                    .toCompletableFuture())
                .collect(Collectors.toList());
                
            return Uni.createFrom().completionStage(
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                    .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()))
            );
}
