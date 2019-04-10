package com.example.tacomanager.repository;

import com.example.tacomanager.models.Ingredient;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IngredientRepository extends PagingAndSortingRepository<Ingredient, String> {
}
