package com.inter.neightbor.domain;

import com.inter.neightbor.dto.ReviewRequestDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
@NoArgsConstructor
public class Review {

    @Id private String id;
    private String content;
    private LocalDateTime createdTime = LocalDateTime.now();

    public Review(ReviewRequestDto reviewRequestDto) {
        this.content = reviewRequestDto.getContent();
    }
}
