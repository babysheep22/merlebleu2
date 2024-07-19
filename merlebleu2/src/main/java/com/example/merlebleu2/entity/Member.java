package com.example.merlebleu2.entity;


import com.example.merlebleu2.constant.Role;
import com.example.merlebleu2.dto.MemberFormDto;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.*;

@Entity
@Table(name="member")
@Getter
@Setter
@ToString
public class Member extends BaseEntity{
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String phonenum;

    private String password;
    private String address1;
    private String address2;
    private Integer postcode;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setPassword(memberFormDto.getPassword());
        member.setPhonenum(memberFormDto.getPhonenum());
        member.setPostcode(memberFormDto.getPostcode());
        member.setAddress1(memberFormDto.getAddress1());
        member.setAddress2(memberFormDto.getAddress2());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.ADMIN);
        return member;

    }
}
