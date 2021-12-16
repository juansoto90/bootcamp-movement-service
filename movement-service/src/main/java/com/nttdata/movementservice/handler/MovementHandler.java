package com.nttdata.movementservice.handler;

import com.nttdata.movementservice.model.entity.Movement;
import com.nttdata.movementservice.service.IMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class MovementHandler {

    private final IMovementService service;

    public Mono<ServerResponse> findByMovementDateBetween(ServerRequest request){
        return service.findByMovementDateBetween()
                .collectList()
                .flatMap(m -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(m)
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findByMovementDateBetweenAndMovementType(ServerRequest request){
        String movementType = request.pathVariable("movementType");
        return service.findByMovementDateBetweenAndMovementType(movementType)
                .collectList()
                .flatMap(m -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(m)
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findByMovementDateBetweenAndAccountNumber(ServerRequest request){
        String accountNumber = request.pathVariable("accountNumber");
        return service.findByMovementDateBetweenAndAccountNumber(accountNumber)
                .collectList()
                .flatMap(m -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(m)
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findByMovementDateBetweenAndMovementTypeAndAccountNumber(ServerRequest request){
        String movementType = request.pathVariable("movementType");
        String movementNumber = request.pathVariable("movementNumber");
        return service.findByMovementDateBetweenAndMovementTypeAndAccountNumber(movementType, movementNumber)
                .collectList()
                .flatMap(m -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(m)
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findById(ServerRequest request){
        String id = request.pathVariable("id");
        return service.findById(id)
                .flatMap(a -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(a)
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> create(ServerRequest request){
        Mono<Movement> movementMono = request.bodyToMono(Movement.class);
        return movementMono.flatMap(service::save)
                .flatMap(m -> ServerResponse.created(URI.create("/movement/".concat(m.getId())))
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .bodyValue(m)
                );
    }
}
