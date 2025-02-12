package com.example;

import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
public class ApiTest {
    
    @Test
    public void testGetDragonBallCharacters() {
        given()
            .when().get("/api/dragonball")
            .then()
            .statusCode(200)
            .body(notNullValue());
    }

    @Test
    public void testGetDigimonByName() {
        given()
            .when().get("/api/digimon/agumon")
            .then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON);
    }
}
