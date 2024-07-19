package com.example.merlebleu2.repository;

import com.example.merlebleu2.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ItemRepository extends JpaRepository<Item, Long>,
        QuerydslPredicateExecutor<Item>, ItemRepositoryCustom {

    List<Item> findByItemNm(String itemNm);


    List<Item> findByCategory1(String category1);

    List<Item> findByCategory2(String category2);


}