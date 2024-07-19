package com.example.merlebleu2.repository;

import com.example.merlebleu2.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long>{
    Cart findByMemberId(Long memberId);

}
