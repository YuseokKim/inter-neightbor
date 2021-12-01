package com.inter.neightbor.handler;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import com.inter.neightbor.domain.Order;
import com.inter.neightbor.dto.OrderRequestDto;
import com.inter.neightbor.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderHandler {

    private final OrderService orderService;

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ok().body(orderService.findAll(), Order.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        String id  = request.pathVariable("id");
        return ok().body(orderService.findById(id), Order.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(OrderRequestDto.class)
                .flatMap(orderRequestDto -> orderService.createOrder(orderRequestDto))
                .doOnNext(order -> log.info("order -> {} ", order))
                .flatMap(order ->
                        ok()
                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(order, Order.class) 이렇게 하면 flatMap이 body세팅을 기다리지 않고 return하기 때문에 notEmpty 에러?가 난다
                        .body(BodyInserters.fromValue(order))
                );
    }
}
