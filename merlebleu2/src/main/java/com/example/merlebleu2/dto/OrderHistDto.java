package com.example.merlebleu2.dto;

import com.example.merlebleu2.constant.OrderStatus;
import com.example.merlebleu2.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class OrderHistDto {

    public OrderHistDto(Order order){
        this.orderId = order.getId();
        this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.orderStatus = order.getOrderStatus();
        this.phonenum = order.getPhonenum(); // 전화번호 추가
        this.address1 = order.getAddress1(); // 주소 추가
        this.address2 = order.getAddress2();
        this.paymentMethod = order.getPaymentMethod();
    }

    private Long orderId; //주문아이디
    private String orderDate; //주문날짜
    private OrderStatus orderStatus; //주문 상태
    private String phonenum; // 전화번호 추가
    private String address1; // 주소 추가
    private String address2;
    private String postcode;

    private String paymentMethod;

    private List<OrderItemDto> orderItemDtoList = new ArrayList<>();

    //주문 상품리스트
    public void addOrderItemDto(OrderItemDto orderItemDto){
        orderItemDtoList.add(orderItemDto);
    }
}
