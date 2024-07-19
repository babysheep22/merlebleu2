package com.example.merlebleu2.entity;

import com.example.merlebleu2.constant.ItemSellStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.example.merlebleu2.exception.OutOfStockException;

import java.time.LocalDateTime;
import com.example.merlebleu2.dto.ItemFormDto;

@Table(name="item")
@Entity
@Getter
@Setter
@ToString
public class Item extends BaseEntity{

    @Id
    @Column(name="item_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //아이디
    private Long id;

    @Column(nullable = false, length = 50)
    //상품이름
    private String itemNm;

    //정가, 판매가 , 할인율
    @Column(nullable = false)
    private Integer listprice;
    @Column(nullable = false)
    private Integer sellprice;
    @Column(nullable = false)
    private Integer discount;



    private int stockNumber; //재고수량


    //카테고리
    @Column(nullable = false)
    private String category1;
    @Column(nullable = false)
    private String category2;

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; //상품 판매 상태



    //상품 업데이트 로직
    public void updateItem(ItemFormDto itemFormDto){
        this.itemNm = itemFormDto.getItemNm();
        this.listprice = itemFormDto.getListprice();
        this.sellprice = itemFormDto.getSellprice();
        this.discount = itemFormDto.getDiscount();
        this.stockNumber = itemFormDto.getStockNumber();
        this.category1 = itemFormDto.getCategory1();
        this.category2 = itemFormDto.getCategory2();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }

    public void removeStock(int stockNumber){
        int restStock = this.stockNumber = stockNumber;
        if(restStock<0) {
            throw new OutOfStockException("상품의 재고가 부족합니다. (현재 재고 수량 : " + this.stockNumber + ")");
        }
        this.stockNumber = restStock;
    }

    public void addStock(int stockNumber){
        this.stockNumber += stockNumber;
    }


}
