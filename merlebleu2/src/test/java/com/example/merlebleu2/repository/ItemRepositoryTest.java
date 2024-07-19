package com.example.merlebleu2.repository;

import com.example.merlebleu2.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest(){
        Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setListprice(20000);
        item.setDiscount(10);
        item.setSellprice(18000);
        item.setCategory1("의류");
        item.setCategory2("아우터");
        item.setRegTime(LocalDateTime.now());
        Item savedItem = itemRepository.save(item);
        System.out.println(savedItem.toString());
    }

    public void createItemList(){
        for(int i=1; i<= 10; i++){
            Item item = new Item();
            item.setItemNm("테스트 상품" + i);
            item.setSellprice(10000+i);
            item.setDiscount(50);
            item.setListprice(5000+i);
            item.setCategory1("의류"+i);
            item.setCategory2("아우터"+i);
            item.setUpdateTime(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);
        }

    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNmTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemNm("테스트 상품1");
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }

    public void findByCategory1(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByCategory1("의류1");
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }

    public void findByCategory2(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByCategory2("아우터");
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }


}