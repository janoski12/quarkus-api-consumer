package com.example.Models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DigimonDTO {
    private String id;
    private String name;
    private List<Description> descriptions;
    private List<Evolution> priorEvolutions;
    private List<Evolution> nextEvolutions;

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Description> getDescriptions() { return descriptions; }
    public void setDescriptions(List<Description> descriptions) { this.descriptions = descriptions; }
    public List<Evolution> getPriorEvolutions() { return priorEvolutions; }
    public void setPriorEvolutions(List<Evolution> priorEvolutions) { this.priorEvolutions = priorEvolutions; }
    public List<Evolution> getNextEvolutions() { return nextEvolutions; }
    public void setNextEvolutions(List<Evolution> nextEvolutions) { this.nextEvolutions = nextEvolutions; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Description {
        private String language;
        private String description;

        // Getters and Setters
        public String getLanguage() { return language; }
        public void setLanguage(String language) { this.language = language; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Evolution {
        private String id;
        private String digimon;

        // Getters and Setters
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getDigimon() { return digimon; }
        public void setDigimon(String digimon) { this.digimon = digimon; }
    }
}
