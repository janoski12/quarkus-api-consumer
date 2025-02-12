package com.example.Controllers;

import java.util.List;

import com.example.Models.DigimonResponse;
import com.example.Models.DragonBallCharacter;
import com.example.Services.DigimonRestTemplate;
import com.example.Services.DigimonWebClientService;
import com.example.Services.DragonBallService;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import reactor.core.publisher.Flux;


@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ApiController {
    
    @Inject
    DragonBallService dragonBallService;

    @Inject
    DigimonWebClientService digimonWebClientService;

    @Inject
    DigimonRestTemplate digimonRestTemplate;

    @GET
    @Path("/dragonball")
    public List<DragonBallCharacter> getSaiyanMales() {
        return dragonBallService.getFilteredCharacters();
    }

    @GET
    @Path("/digimon/webclient")
    public Uni<List<DigimonResponse>> getDigimonWithWebClient() {
        return digimonWebClientService.fetchDigimons();
    }

    @GET
    @Path("/digimon/resttemplate")
    public List<DigimonResponse> getDigimonWithRestTemplate() {
        return digimonRestTemplate.fetchDigimons();
    }
    
}
