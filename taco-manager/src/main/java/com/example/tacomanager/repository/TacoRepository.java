package com.example.tacomanager.repository;

import com.example.tacomanager.models.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
