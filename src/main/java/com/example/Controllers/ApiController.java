package com.example.Controllers;


import java.util.List;
import java.util.Map;

import com.example.Models.DragonBallCharacter;
import com.example.Services.DigimonService;
import com.example.Services.DragonBallService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ApiController {
    
    @Inject
    DragonBallService dragonBallService;

    @Inject
    DigimonService digimonService;

    @GET
    @Path("/dragonball")
    public List<DragonBallCharacter> getSaiyanMales() {
        return dragonBallService.getFilteredCharacters();
    }
    
    @GET
    @Path("/digimon")
    public List<Map<String, Object>> getDigimons() {
        return digimonService.getDigimons();
    }
}
