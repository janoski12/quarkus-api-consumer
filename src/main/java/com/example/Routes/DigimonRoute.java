package com.example.Routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class DigimonRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration()
            .component("platform-http")
            .bindingMode(RestBindingMode.json);

        rest("/api/digimon")
            .get("/fetch")
            .to("direct:fetchDigimons");

        from("direct:fetchDigimons")
            .setBody(constant(List.of("leviamon", "mugendramon", "sakuyamon")))
            .split(body())
            .toD("https://digi-api.com/api/v1/digimon/${body}")
            .unmarshal().json()
            .log("Digimon: ${body}");
    }
}
