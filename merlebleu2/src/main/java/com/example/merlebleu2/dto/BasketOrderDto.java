package com.example.merlebleu2.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BasketOrderDto {
    private Long cartItemId;
    private List<CartOrderDto> cartOrderDtoList = new ArrayList<>();
    private Integer count;
    private String phonenum;
    private String address1;
    private String address2;
    private Integer postcode;
    private String paymentMethod;
    private Long itemId;
    private String itemNm; //아이템 이름
    private Integer sellprice; // 판매가
    private String mainimgurl; //대표 상품 이미지 링크

    @Builder
    public BasketOrderDto(CartOrderDto dto, String itemNm, Integer sellprice, String mainimgurl, Long itemId) {
        this.cartItemId = dto.getCartItemId();
        this.cartOrderDtoList = dto.getCartOrderDtoList();
        this.count = dto.getCount();
        this.phonenum = dto.getPhonenum();
        this.address1 = dto.getAddress1();
        this.address2 = dto.getAddress2();
        this.postcode = dto.getPostcode();
        this.paymentMethod = dto.getPaymentMethod();
        this.itemNm = itemNm;
        this.sellprice = sellprice;
        this.mainimgurl = mainimgurl;
        this.itemId = itemId;
    }
}
