package com.example.merlebleu2.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ItemDto {

    //아이디
    private Long id;

    //상품이름
    private String itemNm;


    //정가, 판매가 , 할인율
    private Integer listprice;
    private Integer sellprice;
    private Integer discount;

    //카테고리
    private String category1;
    private String category2;

    private LocalDateTime regTime;
    private LocalDateTime updateTime;

    //대표상품 이미지 링크
    private Character mainimgurl;
}
