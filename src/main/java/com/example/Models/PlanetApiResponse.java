package com.example.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetApiResponse {

    private List<Planet> items;

    public List<Planet> getItems() {
        return items;
    }

    public void setItems(List<Planet> items) {
        this.items = items;
    }
}
