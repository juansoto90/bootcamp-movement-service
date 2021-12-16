package com.nttdata.movementservice.repository;

import com.nttdata.movementservice.model.entity.Movement;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

public interface IMovementRepository extends ReactiveMongoRepository<Movement, String> {
    public Flux<Movement> findByMovementDateBetween(LocalDateTime from, LocalDateTime to);
    public Flux<Movement> findByMovementDateBetweenAndMovementType(LocalDateTime from, LocalDateTime to, String movementType);
    public Flux<Movement> findByMovementDateBetweenAndAccountNumber(LocalDateTime from, LocalDateTime to, String accountNumber);
    public Flux<Movement> findByMovementDateBetweenAndMovementTypeAndAccountNumber(LocalDateTime from, LocalDateTime to, String movementType, String accountNumber);
}
