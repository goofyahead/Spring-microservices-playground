package com.example.greetings.controller;

import com.example.greetings.models.Ingredient;
import com.example.greetings.repository.IngredientClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/feign/hello")
public class GreetingsFeignController {

    private final IngredientClient ingredientClient;

    public GreetingsFeignController(IngredientClient ingredientClient) {
        this.ingredientClient = ingredientClient;
    }

    @GetMapping
    public List<Ingredient> getIngredients(){
        return ingredientClient.getIngredient();
    }
}
