package com.example.merlebleu2.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class CartOrderDto {
    private Long cartItemId;
    private List<CartOrderDto> cartOrderDtoList = new ArrayList<>();
    private Integer count;
    private String phonenum;
    private String address1;
    private String address2;
    private Integer postcode;
    private String paymentMethod;
}
