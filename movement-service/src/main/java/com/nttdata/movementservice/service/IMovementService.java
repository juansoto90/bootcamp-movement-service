package com.nttdata.movementservice.service;

import com.nttdata.movementservice.model.entity.Movement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface IMovementService {
    public Mono<Movement> findById(String id);
    public Flux<Movement> findByMovementDateBetween();
    public Mono<Movement> save(Movement movement);
    public Flux<Movement> findByMovementDateBetweenAndMovementType(String movementType);
    public Flux<Movement> findByMovementDateBetweenAndAccountNumber(String accountNumber);
    public Flux<Movement> findByMovementDateBetweenAndMovementTypeAndAccountNumber(String movementType, String accountNumber);
}
