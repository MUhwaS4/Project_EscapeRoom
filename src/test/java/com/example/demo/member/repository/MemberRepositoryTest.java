package com.example.demo.member.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.member.entity.Member;

@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;
	
	@Test
	public void 회원등록() {
		Member member = Member.builder()
								.id("user124164654654")
								.password("1234")
								.name("짱아")
								.phone("010-0000-0000")
								.phoneCk("동의")
								.email("a@gmail.com")
								.emailCk(null)
								.role("ROLE_USER")
								.build();
		
		memberRepository.save(member);
	}
	
	@Test
	public void 회원목록조회() {
		List<Member> list = memberRepository.findAll();
		for(Member member : list) {
			System.out.println(member);
		}
	}

	@Test
	public void 회원단건조회() {
		Optional<Member> result = memberRepository.findById("user1");
		if(result.isPresent()) {
			Member member = result.get();
			System.out.println(member);
		}
	}

	@Test
	public void 회원수정() {
		Optional<Member> result = memberRepository.findById("user1");
		Member member = result.get();
		member.setName("감자머리짱구");
		memberRepository.save(member);
	}
	
	@Test
	public void 회원삭제() {
		memberRepository.deleteById("user3");
	}

}