package com.example.ordermanager.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Taco implements Serializable {
    private Long id;
    private Date createdAt;
    private String name;
    private List<Ingredient> ingredients;
    private void createdAt() {
        this.createdAt = new Date();
    }
}