//package com.example.merlebleu2.controller;
//
//import com.example.merlebleu2.service.MemberService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping
//public class MemberCheckController {
//
//    @Autowired
//    private MemberService memberService;
//
//    @PostMapping("/checkEmailDuplicate")
//    public boolean checkEmailDuplicate(@RequestParam("email") String email) {
//        return memberService.isEmailDuplicate(email);
//    }
//
//
//}
