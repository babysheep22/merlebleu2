package com.example.merlebleu2.repository;

import com.example.merlebleu2.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;



public interface MemberRepository extends JpaRepository<Member, Long>{
    Member findByEmail(String email);
}
