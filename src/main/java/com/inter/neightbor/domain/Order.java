package com.inter.neightbor.domain;

import com.inter.neightbor.dto.OrderRequestDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document // 자동으로 collection 생성
@NoArgsConstructor
public class Order {
    // @id 없을 시 id 혹은 _id 라는 멤버 변수가 자등으로 _id 필드로 맵핑
    @Id private String id;
    private String content;
    private String address;
    private LocalDateTime createdDate = LocalDateTime.now();
    private boolean isDone;
    private String comment;
    private Review review;

    public Order(OrderRequestDto orderRequestDto) {
        this.content = orderRequestDto.getContent();
        this.address = orderRequestDto.getAddress();
        this.comment = orderRequestDto.getComment();
    }
}
