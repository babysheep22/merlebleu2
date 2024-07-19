package com.example.merlebleu2.repository;


import com.example.merlebleu2.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderItemRepository  extends  JpaRepository<OrderItem, Long>{
}
