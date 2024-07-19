package com.example.merlebleu2.entity;

import com.example.merlebleu2.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

    private String phonenum; // 전화번호 추가

    private String address1; // 주소 추가

    private String address2;

    private Integer postcode;

    private String paymentMethod; // 결제방식 추가

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public static Order createOrder(Member member, List<OrderItem> orderItemList, String phonenum, Integer postcode, String address1, String address2, String paymentMethod) {
        Order order = new Order();
        order.setMember(member); // 회원정보 세팅
        order.setPhonenum(phonenum); // 전화번호 설정
        order.setPostcode(postcode); // 우편번호 설정
        order.setAddress1(address1); // 기본 주소 설정
        order.setAddress2(address2); // 상세 주소 설정
        order.setPaymentMethod(paymentMethod); // 결제방식 설정
        for (OrderItem orderItem : orderItemList) {
            order.addOrderItem(orderItem);
        }
        order.setOrderStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    public void cancelOrder(){
        this.orderStatus = OrderStatus.CANCEL;
        for(OrderItem orderItem : orderItems){
            orderItem.cancel();
        }
    }
}
