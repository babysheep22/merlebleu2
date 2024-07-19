package com.example.merlebleu2.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartOrderItemDto {
    private Long cartItemId;
    private int count;

    // getters and setters
    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
