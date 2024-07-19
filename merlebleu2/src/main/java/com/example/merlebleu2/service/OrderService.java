package com.example.merlebleu2.service;

import com.example.merlebleu2.dto.*;
import com.example.merlebleu2.entity.*;
import com.example.merlebleu2.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import org.thymeleaf.util.StringUtils;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemImgRepository itemImgRepository;
    private final CartItemRepository cartItemRepository;
    private final ItemService itemService;

    public Long order(OrderDto orderDto, String email) {
        //주문할 상품 조회
        Item item = itemRepository.findById(orderDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);
        //현재 로그인한 회원의 이메일 정보를 이용해서 회원 정보를 조회합니다.
        Member member = memberRepository.findByEmail(email);

        List<OrderItem> orderItemList = new ArrayList<>();
        //주문할 상품 엔티티와 주문 수량을 이용하여 주문 상품 엔티티를 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
        orderItemList.add(orderItem);

        // Order 생성 시 전화번호, 주소, 결제방식 추가
        Order order = Order.createOrder(member, orderItemList, orderDto.getPhonenum(), orderDto.getPostcode(), orderDto.getAddress1(), orderDto.getAddress2(), orderDto.getPaymentMethod());
        orderRepository.save(order);

        return order.getId();
    }

    @Transactional(readOnly = true)
    public Page<OrderHistDto> getOrderList(String email, Pageable pageable) {

        List<Order> orders = orderRepository.findOrders(email, pageable);
        Long totalCount = orderRepository.countOrder(email);

        List<OrderHistDto> orderHistDtos = new ArrayList<>();

        for (Order order : orders) {
            OrderHistDto orderHistDto = new OrderHistDto(order);
            List<OrderItem> orderItems = order.getOrderItems();
            for (OrderItem orderItem : orderItems) {
                ItemImg itemImg = itemImgRepository.findByItemIdAndRepimgYn(orderItem.getItem().getId(), "Y"); //주문한 상품 대표 이미지 조회
                OrderItemDto orderItemDto = new OrderItemDto(orderItem, itemImg.getImgUrl());
                orderHistDto.addOrderItemDto(orderItemDto);
            }
            orderHistDtos.add(orderHistDto);
        }

        return new PageImpl<>(orderHistDtos, pageable, totalCount);
    }

    @Transactional(readOnly = true)
    public boolean validateOrder(Long orderId, String email) {
        Member curMember = memberRepository.findByEmail(email);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        Member savedMember = order.getMember();

        return StringUtils.equals(curMember.getEmail(), savedMember.getEmail());
    }

    //주문 취소
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        order.cancelOrder();
    }

    public Long orders(List<OrderDto> orderDtoList, String email){

        Member member = memberRepository.findByEmail(email);
        List<OrderItem> orderItemList = new ArrayList<>();

        for (OrderDto orderDto : orderDtoList) {
            Item item = itemRepository.findById(orderDto.getItemId())
                    .orElseThrow(EntityNotFoundException::new);

            OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
            orderItemList.add(orderItem);
        }

        if (orderDtoList.isEmpty()) {
            throw new IllegalArgumentException("주문할 상품이 없습니다.");
        }

        OrderDto firstOrderDto = orderDtoList.get(0); // 첫 번째 주문 DTO 가져오기
        Order order = Order.createOrder(member, orderItemList, firstOrderDto.getPhonenum(), firstOrderDto.getPostcode(), firstOrderDto.getAddress1(), firstOrderDto.getAddress2(), firstOrderDto.getPaymentMethod());
        orderRepository.save(order);

        return order.getId();
    }

    public List<BasketOrderDto> getOrderInfo(CartOrderDto request){
        System.err.println("sdfsdfsdfsdfsdffds");
        return request.getCartOrderDtoList().stream().map(this::toBasketOrderDto).collect(Collectors.toList());
    }

    public BasketOrderDto toBasketOrderDto(CartOrderDto dto){
        System.err.println(dto);
        Item item = itemRepository.findById(cartItemRepository.findById(dto.getCartItemId()).orElse(null).getItem().getId())
                .orElse(null);


        System.err.println("item : " + item);
        return BasketOrderDto.builder()
                .dto(dto)
                .itemNm(item.getItemNm())
                .sellprice(item.getSellprice())
                .mainimgurl(itemImgRepository.findByItemIdAndRepimgYn(item.getId(), "Y").getImgUrl())
                .itemId(item.getId())
                .build();

    }


}
