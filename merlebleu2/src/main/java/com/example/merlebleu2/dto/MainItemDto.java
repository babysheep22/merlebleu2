package com.example.merlebleu2.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainItemDto {
    private Long id;

    private String itemNm;

    private String category1;
    private String category2;

    private String imgUrl;

    private Integer sellprice;
    private Integer listprice;
    private Integer discount;

    @QueryProjection //querydsl로 결과 조회 시 MainItemDto 객체로 바로 받아오기
    public MainItemDto(Long id, String itemNm , String category1, String category2 , String imgUrl, Integer sellprice,
                       Integer listprice, Integer discount){
        this.id = id;
        this.itemNm = itemNm;
        this.category1 = category1;
        this.category2 = category2;
        this.imgUrl = imgUrl;
        this.sellprice = sellprice;
        this.listprice = listprice;
        this.discount = discount;
    }
}
