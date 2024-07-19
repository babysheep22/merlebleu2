package com.example.merlebleu2.repository;


import com.example.merlebleu2.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long>{
    List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);

    ItemImg findByItemIdAndRepimgYn(Long itemId, String repimgYn);
    // 구매 이력에서 상품 대표 이미지 보여주기 위해 추가

}
