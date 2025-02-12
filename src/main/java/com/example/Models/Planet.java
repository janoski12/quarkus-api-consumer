package com.example.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Planet {
    private Integer id;
    private String name;
    private String description;
    private String image;
    private List<DragonBallCharacter> characters;  
    private Boolean destroyed;

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<DragonBallCharacter> getCharacters() {
        return characters;
    }

    public void setCharacters(List<DragonBallCharacter> characters) {
        this.characters = characters;
    }

    public Boolean getDestroyed() {
        return destroyed;
    }

    public void setDestroyed(Boolean destroyed) {
        this.destroyed = destroyed;
    }
}