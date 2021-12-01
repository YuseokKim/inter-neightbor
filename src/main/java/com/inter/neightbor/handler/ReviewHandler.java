package com.inter.neightbor.handler;

import com.inter.neightbor.domain.Review;
import com.inter.neightbor.dto.ReviewRequestDto;
import com.inter.neightbor.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReviewHandler {

    private final ReviewService reviewService;

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ok().body(reviewService.findAll(), Review.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        String id  = request.pathVariable("id");
        return ok().body(reviewService.findById(id), Review.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(ReviewRequestDto.class)
                .flatMap(reviewRequestDto -> reviewService.createReview(reviewRequestDto))
                .doOnNext(review -> log.info("review -> {} ", review))
                .flatMap(review -> ok().body(BodyInserters.fromValue(review)));
//                        .body(order, Order.class) 이렇게 하면 flatMap이 body세팅을 기다리지 않고 return하기 때문에 notEmpty 에러?가 난다
    }
}
