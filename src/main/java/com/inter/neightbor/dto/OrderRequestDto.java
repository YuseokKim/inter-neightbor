package com.inter.neightbor.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderRequestDto implements Serializable {

    private String content;
    private String comment;
    private String address;
}
