package com.example;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class DigimonTest {
    @Test
    public void testGetDigimons() {
        given()
            .when()
                .get("/api/digimon")
            .then()
                .statusCode(200)
                .body(notNullValue())
                .body("$", hasSize(3))  // We expect 3 Digimon as per your implementation
                .body("[0].name", notNullValue())
                .body("[0].descriptions", notNullValue());
    }
}
