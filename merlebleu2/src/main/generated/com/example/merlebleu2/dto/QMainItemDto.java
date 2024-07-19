package com.example.merlebleu2.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.merlebleu2.dto.QMainItemDto is a Querydsl Projection type for MainItemDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMainItemDto extends ConstructorExpression<MainItemDto> {

    private static final long serialVersionUID = 180034252L;

    public QMainItemDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> itemNm, com.querydsl.core.types.Expression<String> category1, com.querydsl.core.types.Expression<String> category2, com.querydsl.core.types.Expression<String> imgUrl, com.querydsl.core.types.Expression<Integer> sellprice, com.querydsl.core.types.Expression<Integer> listprice, com.querydsl.core.types.Expression<Integer> discount) {
        super(MainItemDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, String.class, int.class, int.class, int.class}, id, itemNm, category1, category2, imgUrl, sellprice, listprice, discount);
    }

}

