package com.example.tacomanager.controller;

import com.example.tacomanager.models.Taco;
import com.example.tacomanager.repository.TacoRepository;
import com.example.tacomanager.service.TacoMessagingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Flux<Taco> allTacos() {
        log.info("root hit for tacos");
        return Flux.fromIterable(tacoRepo.findAll()).take(5);
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Taco> postTaco(@RequestBody Taco taco) {
        Taco savedTaco = tacoRepo.save(taco);
        tacoMessagingService.sendTaco(taco);
        return Mono.just(savedTaco);

//         return tacoRepo.save(taco).map(savedTaco -> {
//             tacoMessagingService.sendTaco(savedTaco);
//             return savedTaco;
//         });
    }

    @GetMapping("/{id}")
    public Mono<Taco> tacoById(@PathVariable("id") Long id) {
        return Mono.just(tacoRepo.findById(id).get());
//        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/recent")
    public Flux<Taco> recentTacos() {
        return Flux.fromIterable(tacoRepo.findAll()).take(5);
    }
}
