package com.example.Services;

import java.util.concurrent.CompletionStage;

import com.example.Models.DigimonResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@RegisterRestClient(baseUri = "https://digi-api.com/api/v1/digimon")
public interface DigimonRestClient {
        
    @GET
    @Path("/{name}")
    CompletionStage<DigimonResponse> fetchDigimon(@PathParam("name") String name);
}
