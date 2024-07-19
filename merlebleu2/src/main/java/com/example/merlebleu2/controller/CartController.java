package com.example.merlebleu2.controller;
import com.example.merlebleu2.dto.*;
import com.example.merlebleu2.entity.CartItem;
import com.example.merlebleu2.entity.Item;
import com.example.merlebleu2.repository.CartItemRepository;
import com.example.merlebleu2.repository.ItemRepository;
import com.example.merlebleu2.service.CartService;
import com.example.merlebleu2.service.MemberService;
import com.example.merlebleu2.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);
    private final MemberService memberService;
    private final CartService cartService;
    private final OrderService orderService;
    private final ItemRepository itemRepository;
    private final CartItemRepository cartItemRepository;

    @PostMapping(value = "/cart")
    public @ResponseBody ResponseEntity order(@RequestBody @Valid CartItemDto cartItemDto, BindingResult bindingResult, Principal principal) {

        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }

            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        String email = principal.getName();
        Long cartItemId;

        try {
            cartItemId = cartService.addCart(cartItemDto, email);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Long>(cartItemId, HttpStatus.OK);
    }

    //카트 목록
    @GetMapping(value = "/cart")
    public String orderHist(Principal principal, Model model) {
        List<CartDetailDto> cartDetailList = cartService.getCartList(principal.getName());
        model.addAttribute("cartItems", cartDetailList);
        return "cart/cartList";
    }

    @PatchMapping(value = "/cartItem/{cartItemId}")
    public @ResponseBody ResponseEntity updateCartItem(@PathVariable("cartItemId") Long cartItemId, int count, Principal principal) {

        if (count <= 0) {
            return new ResponseEntity<String>("최소 1개 이상 담아주세요", HttpStatus.BAD_REQUEST);
        } else if (!cartService.validateCartItem(cartItemId, principal.getName())) {
            return new ResponseEntity<String>("수정 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        cartService.updateCartItemCount(cartItemId, count);
        return new ResponseEntity<Long>(cartItemId, HttpStatus.OK);
    }

    @DeleteMapping(value = "/cartItem/{cartItemId}")
    public @ResponseBody ResponseEntity deleteCartItem(@PathVariable("cartItemId") Long cartItemId, Principal principal) {

        if (!cartService.validateCartItem(cartItemId, principal.getName())) {
            return new ResponseEntity<String>("수정 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        cartService.deleteCartItem(cartItemId);

        return new ResponseEntity<Long>(cartItemId, HttpStatus.OK);
    }


    @PostMapping("/cart/orders")
    @ResponseBody
    public ResponseEntity orderCartItem(@RequestBody CartOrderDto cartOrderDto, Principal principal) {
        System.err.println("sdfdsfds");
        List<CartOrderDto> cartOrderDtoList = cartOrderDto.getCartOrderDtoList();

        System.err.println(cartOrderDtoList);
        Long orderId = cartService.orderCartItem(cartOrderDtoList, principal.getName());
        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/cart/payment")
    public String cartPayment(@ModelAttribute CartOrderDto cartOrderDto, Model model, Principal principal) {

        System.err.println(cartOrderDto);
        List<CartOrderDto> cartOrderDtoList = cartOrderDto.getCartOrderDtoList();
        if (cartOrderDtoList == null || cartOrderDtoList.isEmpty()) {
            model.addAttribute("errorMessage", "주문할 상품이 없습니다.");
            return "cart/cartList";
        }

        // cartOrderDtoList 내용을 로그로 출력
        logger.debug("cartOrderDtoList: " + cartOrderDtoList);

        // 사용자 정보 가져오기
        MemberFormDto memberFormDto = memberService.getMemberInfo(principal.getName());
        model.addAttribute("member", memberFormDto);

        // 상품 정보 가져오기 cartOrderDtoList >> CartItemId >> ItemId
        List<Item> items = new ArrayList<>();

        for (CartOrderDto dto : cartOrderDtoList) {
            System.err.println("dto:");
            System.err.println(dto);
            logger.debug("Processing cartItemId: " + dto.getCartItemId());
            if (dto.getCartItemId() == null) {
                logger.error("cartItemId is null");
                model.addAttribute("errorMessage", "Invalid cart item ID");
                return "error/404";
            }

            // CartItem 조회
            Optional<CartItem> optionalCartItem = cartItemRepository.findById(dto.getCartItemId());
            if (optionalCartItem.isEmpty()) {
                logger.error("CartItem not found with ID: " + dto.getCartItemId());
                model.addAttribute("errorMessage", "CartItem not found with ID: " + dto.getCartItemId());
                return "error/404";
            }

            CartItem cartItem = optionalCartItem.get();

            // Item 조회 및 초기화
            Item item = cartItem.getItem();
            items.add(item);

        }
//        System.err.println(items);
        model.addAttribute("orders" , orderService.getOrderInfo(cartOrderDto));
//        model.addAttribute("items", items);
//        return "MerleBleu/order/cartpayment";
        return "MerleBleu/order/merlebleu_cartpayment";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException() {
        return "error/403";
    }

}