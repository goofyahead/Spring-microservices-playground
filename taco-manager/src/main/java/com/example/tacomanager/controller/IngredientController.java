package com.example.tacomanager.controller;

import com.example.tacomanager.models.Ingredient;
import com.example.tacomanager.repository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@RequestMapping(path = "/ingredients", produces = "application/json")
public class IngredientController {

    private final IngredientRepository ingredientRepository;

    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping()
    public Flux<Ingredient> getIngredients() {
        log.info("getting ingredients from db");
        return Flux.fromIterable(ingredientRepository.findAll()).take(5);
    }
}
