package com.example.merlebleu2.repository;

import com.example.merlebleu2.dto.CartDetailDto;
import com.example.merlebleu2.dto.CartOrderDto;
import com.example.merlebleu2.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartIdAndItemId(Long cartId, Long itemId); // 카트 아이디와 상품 아이디를 이용해서 상품이 장바구니에 들어있는지 조회
    @Query("select new com.example.merlebleu2.dto.CartDetailDto(ci.id, i.itemNm, i.sellprice, ci.count, im.imgUrl) " +
            "from CartItem ci, ItemImg im " +
            "join ci.item i " +
            "where ci.cart.id = :cartId " +
            "and im.item.id = ci.item.id " +
            "and im.repimgYn = 'Y' " +
            "order by ci.regTime desc"
    )
    List<CartDetailDto> findCartDetailDtoList(@Param("cartId")Long cartId);

    @Query("select new com.example.merlebleu2.dto.CartOrderDto(ci.id, i.itemNm, ci.count, i.sellprice) " +
            "from CartItem ci " +
            "join ci.item i " +
            "where ci.id in :cartItemIds")
    List<CartOrderDto> findCartOrderDtoList(@Param("cartItemIds") List<Long> cartItemIds);


    List<CartItem> findByIdIn(List<Long> cartItemIds);
}
