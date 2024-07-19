package com.example.merlebleu2.dto;

import com.example.merlebleu2.constant.ItemSellStatus;
import com.example.merlebleu2.entity.Item;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ItemFormDto {

    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemNm;

    @NotNull(message = "정가는 필수 입력 값입니다.")
    private Integer listprice;

    @NotNull(message = "할인율은 필수 입력 값입니다.")
    private Integer discount;

    @NotNull(message = "판매가는 필수 입력 값입니다.")
    private Integer sellprice;

    @NotBlank(message = "카테고리1는 필수 입력 값입니다.")
    private String category1;

    @NotBlank(message = "카테고리2는 필수 입력 값입니다.")
    private String category2;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Item createItem(){
        return modelMapper.map(this, Item.class);
    }

    public static ItemFormDto of(Item item){
        return modelMapper.map(item, ItemFormDto.class);
    }

}
