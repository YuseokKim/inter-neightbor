package com.inter.neightbor.router;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.inter.neightbor.handler.OrderHandler;
import com.inter.neightbor.handler.ReviewHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class HomeRouter {

    @Bean
    public RouterFunction<ServerResponse> orderRouter(OrderHandler handler) {
        return route()
                .GET("/order", accept(APPLICATION_JSON), handler::findAll)
                .GET("/order/{id}", accept(APPLICATION_JSON), handler::findById)
                .POST("/order", accept(APPLICATION_JSON).and(contentType(APPLICATION_JSON)), handler::save)
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> reviewRouter(ReviewHandler handler) {
        return route()
                .GET("/review", accept(APPLICATION_JSON), handler::findAll)
                .GET("/review/{id}", accept(APPLICATION_JSON), handler::findById)
                .POST("/review", accept(APPLICATION_JSON).and(contentType(APPLICATION_JSON)), handler::save)
                .build();
    }
}
