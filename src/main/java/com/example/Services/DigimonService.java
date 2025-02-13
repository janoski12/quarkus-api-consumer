package com.example.Services;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;


import com.example.Models.DigimonDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DigimonService extends RouteBuilder {
    
    @Inject
    ProducerTemplate producerTemplate;

    @Override
    public void configure() {
        restConfiguration()
            .component("http");

        from("direct:getDigimon")
            .setHeader("CamelHttpMethod", constant("GET"))
            .toD("https://digi-api.com/api/v1/digimon/${header.digimonName}")
            .convertBodyTo(String.class);
    }

public List<Map<String, Object>> getDigimons() {
    List<String> digimonNames = Arrays.asList("leviamon", "mugendramon", "sakuyamon");
    ObjectMapper mapper = new ObjectMapper();
    
    return digimonNames.stream()
        .map(name -> {
            try {
                String response = producerTemplate.requestBodyAndHeader(
                    "direct:getDigimon",
                    null,
                    "digimonName",
                    name,
                    String.class
                );
                
                DigimonDTO digimon = mapper.readValue(response, DigimonDTO.class);
                
                // Create a new map with the required fields
                Map<String, Object> result = new HashMap<>();
                result.put("name", digimon.getName());

                System.out.println("Digimon descriptions: " + digimon.getDescriptions());
                
                // Filter English description
                if (digimon.getDescriptions() != null) {
                    List<Map<String, String>> descriptions = digimon.getDescriptions().stream()
                        .filter(desc -> "en_us".equals(desc.getLanguage()))
                        .map(desc -> {
                            Map<String, String> descMap = new HashMap<>();
                            descMap.put("language", desc.getLanguage());
                            descMap.put("description", desc.getDescription());
                            return descMap;
                        })
                        .collect(Collectors.toList());
                    result.put("descriptions", descriptions);
                }
                
                // Map prior evolutions
                List<Map<String, String>> priorEvolutions = digimon.getPriorEvolutions().stream()
                    .map(evo -> {
                        Map<String, String> evoMap = new HashMap<>();
                        evoMap.put("id", evo.getId());
                        evoMap.put("digimon", evo.getDigimon());
                        return evoMap;
                    })
                    .collect(Collectors.toList());
                result.put("priorEvolutions", priorEvolutions);
                
                // Map next evolutions
                List<Map<String, String>> nextEvolutions = digimon.getNextEvolutions().stream()
                    .map(evo -> {
                        Map<String, String> evoMap = new HashMap<>();
                        evoMap.put("id", evo.getId());
                        evoMap.put("digimon", evo.getDigimon());
                        return evoMap;
                    })
                    .collect(Collectors.toList());
                result.put("nextEvolutions", nextEvolutions);
                
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        })
        .filter(Objects::nonNull)
        .sorted(Comparator.comparing(map -> (String) map.get("name")))
        .collect(Collectors.toList());
}
}
