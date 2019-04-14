package com.example.tacomanager.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdAt;

    @OneToMany(targetEntity = Taco.class)
    @Size(min = 1, message = "It must contain at least 1 taco")
    private List<Taco> tacos;

    @PrePersist
    private void createdAt() {
        this.createdAt = new Date();
    }
}
