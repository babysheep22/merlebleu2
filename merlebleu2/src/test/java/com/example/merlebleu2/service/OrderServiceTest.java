package com.example.merlebleu2.service;
import com.example.merlebleu2.constant.ItemSellStatus;
import com.example.merlebleu2.constant.OrderStatus;
import com.example.merlebleu2.dto.OrderDto;
import com.example.merlebleu2.entity.Item;
import com.example.merlebleu2.entity.Member;
import com.example.merlebleu2.entity.Order;
import com.example.merlebleu2.entity.OrderItem;
import com.example.merlebleu2.repository.ItemRepository;
import com.example.merlebleu2.repository.MemberRepository;
import com.example.merlebleu2.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

    public Item saveItem(){ //테스트를 위해 주문할 상품 저장하는 메소드 생성
        Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setSellprice(5000);
        item.setCategory1("테스트카테고리1");
        item.setCategory2("테스트카테고리2");
        item.setListprice(10000);
        item.setDiscount(50);
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        return itemRepository.save(item);
    }

    public Member saveMember(){ // 테스트를 위한 회원 세팅
        Member member = new Member();
        member.setEmail("test@test.com");
        return memberRepository.save(member);

    }

    @Test
    @DisplayName("주문 테스트")
    public void order(){
        Item item = saveItem();
        Member member = saveMember();

        OrderDto orderDto = new OrderDto();
        orderDto.setCount(10); //주문할 상품과 상품 수량을 orderDto 객체에 세팅합니다.
        orderDto.setItemId(item.getId());

        Long orderId = orderService.order(orderDto, member.getEmail()); //주문 로직 호출 결과 생성된 주문 번호를 orderId변수에 저장합니다.
        Order order = orderRepository.findById(orderId) //주문 번호를 이용하여 저장된 주문 정보를 조회
                .orElseThrow(EntityNotFoundException::new);

        List<OrderItem> orderItems = order.getOrderItems();

        int totalPrice = orderDto.getCount()*item.getSellprice(); //총가격

        assertEquals(totalPrice, order.getTotalPrice()); //총가격과 데이터베이스에 저장된 상품 가격을 비교하여 같으면 테스트 종료
    }
}