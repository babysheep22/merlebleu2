package com.example.merlebleu2.repository;

import com.example.merlebleu2.dto.ItemSearchDto;
import com.example.merlebleu2.dto.MainItemDto;
import com.example.merlebleu2.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

}
