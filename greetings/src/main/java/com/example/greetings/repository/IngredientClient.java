package com.example.greetings.repository;

import com.example.greetings.models.Ingredient;
import com.example.greetings.utilities.Constants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(Constants.TACO_SERVER_NAME)
public interface IngredientClient {

    @GetMapping("/ingredients")
    List<Ingredient> getIngredient();
}
