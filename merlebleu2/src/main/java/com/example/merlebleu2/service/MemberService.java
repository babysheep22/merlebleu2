package com.example.merlebleu2.service;

import com.example.merlebleu2.dto.MemberFormDto;
import com.example.merlebleu2.entity.Member;
import com.example.merlebleu2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;




@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService{

    private final MemberRepository memberRepository;

    public Member saveMember(Member member){ //멤버 저장
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member){       //예외처리
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override //UserDetailService 인터페이스의 loadUserByUsername() 메소드 오버라이딩
    public UserDetails loadUserByUsername(String email) throws
            UsernameNotFoundException{
        Member member = memberRepository.findByEmail(email);

        if (member == null) {

            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }

    public MemberFormDto getMemberInfo(String email) {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new IllegalStateException("회원 정보를 찾을 수 없습니다.");
        }

        //회원 정보 가져오기
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail(member.getEmail());
        memberFormDto.setName(member.getName());
        memberFormDto.setPhonenum(member.getPhonenum());
        memberFormDto.setPostcode(member.getPostcode());
        memberFormDto.setAddress1(member.getAddress1());
        memberFormDto.setAddress2(member.getAddress2());

        return memberFormDto;
    }

    //
//    public Member isEmailDuplicate(email){
//
//    }
}
