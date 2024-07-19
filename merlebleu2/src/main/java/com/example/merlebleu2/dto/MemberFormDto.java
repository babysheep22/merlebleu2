package com.example.merlebleu2.dto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberFormDto {

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;
    @NotEmpty(message = "이메일은 필수 입력값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "전화번호는 필수 입력 값입니다.")
    @Length(min = 9, max = 11, message = "비밀번호는 9자 이상 11자 이하로 입력해주세요.")
    private String phonenum;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
    private String password;


    //기본주소
    @NotEmpty(message = "주소는 필수 입력 값입니다.")
    private String address1;
    //상세주소
    @NotEmpty(message = "주소는 필수 입력 값입니다.")
    private String address2;
    //우편번호
    private Integer postcode;

    //마일리지
   // private String milage;

}
