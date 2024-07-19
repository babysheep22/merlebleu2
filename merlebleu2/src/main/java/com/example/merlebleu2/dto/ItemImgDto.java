package com.example.merlebleu2.dto;


import com.example.merlebleu2.entity.ItemImg;
import lombok.Setter;
import lombok.Getter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class ItemImgDto {
    private Long id;
    private String oriImgName;
    private String imgUrl;
    private String repImgYn;
    private static ModelMapper modelMapper = new ModelMapper();
    //멤버 변수로 ModelMapper 객체 추가
    public static ItemImgDto of(ItemImg itemImg){
        return modelMapper.map(itemImg, ItemImgDto.class);
    }
}
