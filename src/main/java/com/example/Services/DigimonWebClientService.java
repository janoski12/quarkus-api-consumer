package com.example.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.Models.DigimonResponse;

import reactor.core.publisher.Flux;

@Service
public class DigimonWebClientService {
    private final WebClient webClient;

    public DigimonWebClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://digi-api.com/api/v1/digimon").build();
    }

    public Flux<DigimonResponse> fetchDigimons() {
        List<String> digimons = List.of("leviamon", "mugendramon", "sakuyamon");

        return Flux.fromIterable(digimons)
                .flatMap(name -> webClient.get()
                        .uri("/{name}", name)
                        .retrieve()
                        .bodyToMono(DigimonResponse.class))
                .map(this::filterDigimonData)
                .sort((a, b) -> a.getName().compareToIgnoreCase(b.getName())); // Ordenamos por nombre
    }

    private DigimonResponse filterDigimonData(DigimonResponse digimon) {
        digimon.setDescriptions(digimon.getDescriptions().stream()
                .filter(d -> "en".equalsIgnoreCase(d.getLanguage()))
                .collect(Collectors.toList()));

        return digimon;
    }
}
