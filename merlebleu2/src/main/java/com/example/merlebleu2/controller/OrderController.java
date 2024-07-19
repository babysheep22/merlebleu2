package com.example.merlebleu2.controller;

import com.example.merlebleu2.dto.*;
import com.example.merlebleu2.entity.Item;
import com.example.merlebleu2.repository.ItemRepository;
import com.example.merlebleu2.service.ItemService;
import com.example.merlebleu2.service.MemberService;
import com.example.merlebleu2.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import java.util.Optional;
import jakarta.persistence.EntityNotFoundException;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final MemberService memberService;
    private final OrderService orderService;
    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @PostMapping(value = "/order")
    public @ResponseBody ResponseEntity order(@RequestBody @Valid OrderDto orderDto
            , BindingResult bindingResult, Principal principal){

        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }

            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        String email = principal.getName();
        Long orderId;

        try {
            orderId = orderService.order(orderDto, email);
        } catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/order/payment")
    public String paymentPage(@RequestParam Long itemId, @RequestParam Integer count, Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/members/login";
        }
        // 사용자 정보 가져오기
        MemberFormDto memberFormDto = memberService.getMemberInfo(principal.getName());
        model.addAttribute("member", memberFormDto);

        // 상품 정보 가져오기
        Item item = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);
        model.addAttribute("item" , item);

        OrderDto orderDto = new OrderDto();
        orderDto.setItemId(itemId);
        orderDto.setCount(count);
        model.addAttribute("order", orderDto);


//        return "MerleBleu/order/payment"; // 결제 페이지로 이동
        return "MerleBleu/order/merlebleu_payment";
    }

    //주문내역
    @GetMapping(value = {"/orders", "/orders/{page}" , "/merlebleu/order-details"})
    public String orderHist(@PathVariable("page") Optional<Integer> page, Principal principal, Model model){

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 4);
        Page<OrderHistDto> ordersHistDtoList = orderService.getOrderList(principal.getName(), pageable);

        model.addAttribute("orders", ordersHistDtoList);
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("maxPage", 5);

        return "order/orderHist";
    }

    @PostMapping("/order/{orderId}/cancel")
    public @ResponseBody ResponseEntity cancelOrder(@PathVariable("orderId") Long orderId , Principal principal){

        if(!orderService.validateOrder(orderId, principal.getName())){
            return new ResponseEntity<>("주문 취소 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        orderService.cancelOrder(orderId);
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }
}
