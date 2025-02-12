package com.example.dtos;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DragonBallCharacter {

    private int id;

    private String name;
    private String race;
    private String gender;

    @JsonProperty("ki")
    private String ki;

    @JsonProperty("maxKi")
    private String maxKi;

    private String description;
    private String image;
    private String affiliation;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Planet> originPlanet;

    public DragonBallCharacter() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRace() {
        return race;
    }
    public void setRace(String race) {
        this.race = race;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getKi() {
        return ki;
    }
    public void setKi(String ki) {
        this.ki = ki;
    }
    public String getMaxKi() {
        return maxKi;
    }
    public void setMaxKi(String maxKi) {
        this.maxKi = maxKi;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getAffiliation() {
        return affiliation;
    }
    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }
    public List<Planet> getOriginPlanet() {
        return originPlanet;
    }

    public void setOriginPlanet(List<Planet> originPlanet) {
        this.originPlanet = originPlanet;
    }


}
