package com.example.tacomanager.controller;

import com.example.tacomanager.models.Taco;
import com.example.tacomanager.repository.TacoRepository;
import com.example.tacomanager.service.TacoMessagingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/tacos", produces = "application/json")
public class TacoController {

    private final TacoRepository tacoRepo;
    private final TacoMessagingService tacoMessagingService;

    public TacoController(TacoRepository tacoRepo, TacoMessagingService tacoMessagingService) {
        this.tacoRepo = tacoRepo;
        this.tacoMessagingService = tacoMessagingService;
    }

    @GetMapping()
    public Iterable<Taco> allTacos() {
        log.info("root hit for tacos");
        return tacoRepo.findAll();
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        Taco savedTaco = tacoRepo.save(taco);
        tacoMessagingService.sendTaco(savedTaco);
        return savedTaco;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepo.findById(id);

        if (optTaco.isPresent()) {
            return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/recent")
    public Iterable<Taco> recentTacos() {
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        return tacoRepo.findAll(page).getContent();
    }
}
