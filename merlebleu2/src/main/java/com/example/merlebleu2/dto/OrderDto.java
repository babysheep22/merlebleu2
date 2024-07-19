package com.example.merlebleu2.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDto {

    private String itemNm;

    @NotNull(message = "상품 아이디는 필수 입력 값입니다.")
    private Long itemId;

    @NotNull(message = "주문 수량은 필수 입력 값입니다.")
    private Integer count;

    // 전화번호 추가
    @NotEmpty(message = "전화번호는 필수 입력 값입니다.")
    private String phonenum;

    // 주소 추가
    @NotEmpty(message = "주소1는 필수 입력 값입니다.")
    private String address1;

    @NotEmpty(message = "주소2는 필수 입력 값입니다.")
    private String address2;

    @NotNull(message = "우편번호는 필수 입력 값입니다.")
    private Integer postcode;


    // 결제방식 추가
    @NotEmpty(message = "결제방식은 필수 입력 값입니다.")
    private String paymentMethod;

}
