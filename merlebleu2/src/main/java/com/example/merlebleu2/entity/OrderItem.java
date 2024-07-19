package com.example.merlebleu2.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class OrderItem extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; //주문가격

    private int count; //수량

//    private LocalDateTime regTime;
//    private LocalDateTime updateTime; BaseEntity에서 상속받으므로 삭제

    public static OrderItem createOrderItem(Item item, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item); //주문할 상품 세팅
        orderItem.setCount(count); //주문할 수량 세팅


        orderItem.setOrderPrice(item.getSellprice()); // 상품 가격을 주문 가격으로 세팅

        item.removeStock(count);
        return orderItem;
    }

    public int getTotalPrice(){
        return orderPrice*count; //주문 가격 * 수량  = 총가격
    }

    public void cancel() {
        this.getItem().addStock(count);
    }

}