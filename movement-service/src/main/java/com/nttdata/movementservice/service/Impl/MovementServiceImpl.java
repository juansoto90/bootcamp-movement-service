package com.nttdata.movementservice.service.Impl;

import com.nttdata.movementservice.model.entity.Movement;
import com.nttdata.movementservice.repository.IMovementRepository;
import com.nttdata.movementservice.service.IMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class MovementServiceImpl implements IMovementService {

    private final IMovementRepository repository;

    @Override
    public Mono<Movement> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Movement> findByMovementDateBetween() {
        String year = String.valueOf(LocalDateTime.now().getYear());
        String month = String.valueOf(LocalDateTime.now().getMonthValue());
        String str = year+"-"+month+"-01 00:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime from = LocalDateTime.parse(str, formatter);
        LocalDateTime to = LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter);
        return repository.findByMovementDateBetween(from, to);
    }

    @Override
    public Mono<Movement> save(Movement movement) {
        return repository.save(movement);
    }

    @Override
    public Flux<Movement> findByMovementDateBetweenAndMovementType(String movementType) {
        String year = String.valueOf(LocalDateTime.now().getYear());
        String month = String.valueOf(LocalDateTime.now().getMonthValue());
        String str = year+"-"+month+"-01 00:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime from = LocalDateTime.parse(str, formatter);
        LocalDateTime to = LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter);
        return repository.findByMovementDateBetweenAndMovementType(from, to, movementType);
    }

    @Override
    public Flux<Movement> findByMovementDateBetweenAndAccountNumber(String accountNumber) {
        String year = String.valueOf(LocalDateTime.now().getYear());
        String month = String.valueOf(LocalDateTime.now().getMonthValue());
        String str = year+"-"+month+"-01 00:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime from = LocalDateTime.parse(str, formatter);
        LocalDateTime to = LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter);
        return repository.findByMovementDateBetweenAndAccountNumber(from, to, accountNumber);
    }

    @Override
    public Flux<Movement> findByMovementDateBetweenAndMovementTypeAndAccountNumber(String movementType, String accountNumber) {
        String year = String.valueOf(LocalDateTime.now().getYear());
        String month = String.valueOf(LocalDateTime.now().getMonthValue());
        String str = year+"-"+month+"-01 00:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime from = LocalDateTime.parse(str, formatter);
        LocalDateTime to = LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter);
        return repository.findByMovementDateBetweenAndMovementTypeAndAccountNumber(from, to, movementType, accountNumber);
    }
}
