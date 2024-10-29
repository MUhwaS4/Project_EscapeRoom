package com.example.demo.qna.service;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.member.entity.Member;
import com.example.demo.qna.entity.Qna;
import com.example.demo.qna.repository.QnaRepository;

@SpringBootTest
public class QnaServiceTest {
	
	@Autowired
	QnaService qnaService;
	
	@Autowired
	QnaRepository qnaRepository;
	
	
	@Test
	void 게시물등록() {
		
		Member member = Member.builder().id("user1").build();

		Qna qna = Qna.builder()
					.title("안녕하세요")
					.content("안녕하세요")
					.id(member)
					.build();
		qnaRepository.save(qna);
		
		// 한명의 회원이 여러개의 게시물을 작성할 수 있음
		Qna qna2 = Qna.builder()
					.title("반갑습니다")
					.content("반갑습니다")
					.id(member)
					.build();
		qnaRepository.save(qna2);
	}

	
	@Test
	public void 게시물조회() {
		
		Optional<Qna> optional = qnaRepository.findById(1);
		
		Qna qna = optional.get();
		
		System.out.println(qna);
		
	}

}





