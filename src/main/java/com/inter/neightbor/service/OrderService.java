package com.inter.neightbor.service;

import com.inter.neightbor.domain.Order;
import com.inter.neightbor.dto.OrderRequestDto;
import com.inter.neightbor.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Flux<Order> findAll() {
        return orderRepository.findAll();
    }

    public Mono<Order> findById(String id) {
        return orderRepository.findById(id);
    }

    public Mono<Order> createOrder(OrderRequestDto orderRequestDto) {
        Order order = new Order(orderRequestDto);
        return orderRepository.insert(order);
    }
}
