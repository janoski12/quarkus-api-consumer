package com.example.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.Models.DragonBallCharacter;
import com.example.Models.Planet;
import com.example.Models.PlanetApiResponse;

import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class DragonBallService {
    private final RestTemplate restTemplate;
    private static final String BASE_URL = "https://dragonball-api.com/api/characters";
    private static final String PLANET_URL = "https://dragonball-api.com/api/planets/";

    public DragonBallService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<DragonBallCharacter> getFilteredCharacters() {

        List<DragonBallCharacter> characters = fetchCharacters();
        List<Planet> allPlanets = fetchAllPlanetsIndividually();
        
        // Debug: Imprimir información de Raditz
        characters.stream()
            .filter(c -> "Raditz".equals(c.getName()))
            .findFirst()
            .ifPresent(raditz -> {
                System.out.println(" Información de Raditz:");
                System.out.println("   ID: " + raditz.getId());
                System.out.println("   Nombre: " + raditz.getName());
                System.out.println("   Raza: " + raditz.getRace());
            });

        // Debug: Imprimir información de planetas y sus personajes
        System.out.println("\n Información de planetas y sus personajes:");
        allPlanets.forEach(planet -> {
            System.out.println("\nPlaneta: " + planet.getName());
            System.out.println("ID: " + planet.getId());
            if (planet.getCharacters() != null) {
                System.out.println("Personajes asociados: " + planet.getCharacters());
            } else {
                System.out.println("No tiene personajes asociados");
            }
        });

        // Crear mapa de ID de personaje a planeta
        Map<String, List<Planet>> characterPlanetMap = new HashMap<>();
        
        // Para cada planeta, revisar su lista de characters y crear el mapeo
        for (Planet planet : allPlanets) {
            if (planet.getCharacters() != null) {
                for (DragonBallCharacter character : planet.getCharacters()) {
                    String characterName = character.getName();
                    characterPlanetMap
                        .computeIfAbsent(characterName, k -> new ArrayList<>())
                        .add(planet);
                }
            }
        }

        // Asignar planetas a los personajes
        return characters.stream()
                .map(character -> {
                    if ("Army of Frieza".equalsIgnoreCase(character.getAffiliation())) {
                        // Convertir el ID del personaje a String para la búsqueda
                        String characterName = character.getName();

                        // Debug: Imprimir búsqueda de planeta
                        System.out.println("\n Buscando planeta para " + character.getName() + " (Name: " + characterName + ")");
                        
                        List<Planet> characterPlanets = characterPlanetMap.get(characterName);
                        
                        if (characterPlanets != null && !characterPlanets.isEmpty()) {
                            character.setOriginPlanet(characterPlanets);
                            System.out.println(" Personaje: " + character.getName() + 
                                             " | Planetas encontrados: " + 
                                             characterPlanets.stream()
                                                 .map(Planet::getName)
                                                 .collect(Collectors.joining(", ")));
                        } else {
                            System.out.println(" No se encontraron planetas para: " + character.getName());
                            // Crear planeta desconocido si no se encuentra ninguno
                            Planet unknown = new Planet();
                            unknown.setId(null);
                            unknown.setName("Desconocido");
                            unknown.setDescription(null);
                            unknown.setImage(null);
                            unknown.setCharacters(null);
                            unknown.setDestroyed(null);
                            character.setOriginPlanet(List.of(unknown));
                        }
                    }
                    return character;
                })
                .collect(Collectors.toList());
    }

    private List<Planet> fetchAllPlanetsIndividually() {
        ResponseEntity<PlanetApiResponse> response = restTemplate.getForEntity(
                PLANET_URL, 
                PlanetApiResponse.class
        );
    
        if (response.getBody() == null || response.getBody().getItems() == null) {
            throw new RuntimeException("No planet data found from API response");
        }
    
        List<Planet> planetList = new ArrayList<>();
    
        for (Planet planet : response.getBody().getItems()) {
            String planetUrl = PLANET_URL + planet.getId();
            try {
                
                Planet fullPlanet = restTemplate.getForObject(planetUrl, Planet.class);
                
                if (fullPlanet != null) {
                    planetList.add(fullPlanet);
                }
            } catch (Exception e) {
                System.err.println(" Error obteniendo detalles del planeta ID " + planet.getId() + ": " + e.getMessage());
            }
        }
    
        System.out.println(" Planetas obtenidos: " + planetList.size());
        return planetList;
    }
    

    private List<DragonBallCharacter> fetchCharacters() {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("gender", "Male")
                .queryParam("race", "Saiyan")
                .toUriString();

        ResponseEntity<List<DragonBallCharacter>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DragonBallCharacter>>() {}
        );

        if (response.getBody() == null) {
            throw new RuntimeException("No character data from API response");
        }
        
        List<DragonBallCharacter> characters = response.getBody();
        System.out.println(" Personajes obtenidos: " + characters.size());
        return characters;
    }
}
