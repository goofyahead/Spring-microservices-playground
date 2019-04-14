package com.example.greetings.controller;

import com.example.greetings.models.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@RequestMapping(path = "/api/hello", produces = "application/json")
public class GreetingsController {

    private final WebClient.Builder wcBuilder;

    public GreetingsController(WebClient.Builder wcBuilder) {
        this.wcBuilder = wcBuilder;
    }

    @GetMapping()
    public Flux<Ingredient> getIngredients() {
        log.info("called getIngredients");
        return wcBuilder.build()
                .get()
                .uri("http://taco-manager/ingredients")
                .retrieve().bodyToFlux(Ingredient.class);
    }
}
