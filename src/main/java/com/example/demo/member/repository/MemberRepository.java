package com.example.demo.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
	
	Optional<Member> findById(String id); // 아이디로 회원 조회
	
	
	}
