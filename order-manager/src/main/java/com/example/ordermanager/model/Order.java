package com.example.ordermanager.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Order implements Serializable {

    private Long id;
    private Date createdAt;
    private List<Taco> tacos;

    private void createdAt() {
        this.createdAt = new Date();
    }
}

