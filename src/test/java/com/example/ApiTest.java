package com.example;

import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;


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

}
