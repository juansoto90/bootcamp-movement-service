package com.nttdata.movementservice.config;

import com.nttdata.movementservice.handler.MovementHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import org.springframework.web.reactive.function.server.RouterFunction;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {
    @Bean
    public RouterFunction<ServerResponse> routes(MovementHandler handler){
        return route(GET("/movement"), handler::findByMovementDateBetween)
                .andRoute(GET("/movement/movement-type/{movementType}"), handler::findByMovementDateBetweenAndMovementType)
                .andRoute(GET("/movement/account-number/{accountNumber}"), handler::findByMovementDateBetweenAndAccountNumber)
                .andRoute(GET("/movement/movement-type-number/{movementType}/{movementNumber}"), handler::findByMovementDateBetweenAndMovementTypeAndAccountNumber)
                .andRoute(POST("/movement"), handler::create);
    }
}
