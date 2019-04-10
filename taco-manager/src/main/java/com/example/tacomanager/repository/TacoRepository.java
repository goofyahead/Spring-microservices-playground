package com.example.tacomanager.repository;

import com.example.tacomanager.models.Taco;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {
}
