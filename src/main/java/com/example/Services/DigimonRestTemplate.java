package com.example.Services;


import org.springframework.web.client.RestTemplate;


import com.example.Models.DigimonResponse;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class DigimonRestTemplate {
    private final RestTemplate restTemplate;

    public DigimonRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<DigimonResponse> fetchDigimons() {
        List<String> digimons = List.of("leviamon", "mugendramon", "sakuyamon");

        return digimons.stream()
                .map(name -> restTemplate.getForObject("https://digi-api.com/api/v1/digimon/{name}", DigimonResponse.class, name))
                .map(this::filterDigimonData)
                .sorted((a, b) -> a.getName().compareToIgnoreCase(b.getName())) // Ordenamos por nombre
                .collect(Collectors.toList());
    }

    private DigimonResponse filterDigimonData(DigimonResponse digimon) {
        digimon.setDescriptions(digimon.getDescriptions().stream()
                .filter(d -> "EN".equalsIgnoreCase(d.getLanguage()))
                .collect(Collectors.toList()));
        return digimon;
    }
}
