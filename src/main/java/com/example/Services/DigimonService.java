package com.example.Services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DigimonService {
    
    private final RestTemplate restTemplate;

    public DigimonService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getDigimonByName(String name) {
        String url = String.format("https://digi-api.com/api/v1/digimon/%s", name);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
        }
}
