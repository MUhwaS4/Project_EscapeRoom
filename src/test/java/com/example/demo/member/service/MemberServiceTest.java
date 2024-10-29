package com.example.demo.member.service;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.comment.entity.Comment;
import com.example.demo.comment.repository.CommentRepository;
import com.example.demo.comment.service.CommentService;
import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.entity.Member;
import com.example.demo.qna.dto.QnaDTO;
import com.example.demo.qna.entity.Qna;
import com.example.demo.qna.repository.QnaRepository;
import com.example.demo.qna.service.QnaService;

@SpringBootTest
public class MemberServiceTest {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	CommentService commentService;
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	QnaService qnaService;
	@Autowired
	QnaRepository qnaRepository;
	
	@Test
	public void 회원등록() {
		
		LocalDateTime dateTime = LocalDateTime.now();
		
		MemberDTO dto1 = MemberDTO.builder()
							.id("user1")
							.password("1234")
							.name("짱구")
							.birthday("000828")
							.phone("010-1111-1111")
							.phoneCk("동의")
							.email("xx@naver.com")
							.emailCk("동의")
							.role("ROLE_USER")
							.regDate(dateTime)
							.modDate(dateTime)
							.build();
		
		MemberDTO dto2 = MemberDTO.builder()
							.id("user2")
							.password("1234")
							.name("짱아")
							.birthday("050228")
							.phone("010-1111-1111")
							.email("xx@naver.com")
							.role("ROLE_USER")
							.regDate(dateTime)
							.modDate(dateTime)
							.build();
		
		MemberDTO dto3 = MemberDTO.builder()
							.id("user3")
							.password("1234")
							.name("프시케")
							.birthday("060512")
							.phone("010-1111-1111")
							.phoneCk("동의")
							.role("ROLE_USER")
							.regDate(dateTime)
							.modDate(dateTime)
							.build();
		
		MemberDTO dto4 = MemberDTO.builder()
							.id("user4")
							.password("1234")
							.name("다프네")
							.birthday("901128")
							.phone("010-1111-1111")
							.email("xx@naver.com")
							.emailCk("동의")
							.role("ROLE_USER")
							.regDate(dateTime)
							.modDate(dateTime)
							.build();
		
		MemberDTO dto5 = MemberDTO.builder()
							.id("user5")
							.password("1234")
							.name("이로하")
							.birthday("950602")
							.phone("010-1111-1111")
							.phoneCk("동의")
							.role("ROLE_USER")
							.regDate(dateTime)
							.modDate(dateTime)
							.build();
	
		memberService.register(dto1);
		memberService.register(dto2);
		memberService.register(dto3);
		memberService.register(dto4);
		memberService.register(dto5);
		
	}
	
	@Test
	public void 테스트일괄등록() {
		
		LocalDateTime dateTime = LocalDateTime.now();
		
		MemberDTO dtoTest = MemberDTO.builder()
							.id("testUser")
							.password("1234")
							.name("테스트 이름")
							.birthday("000828")
							.phone("010-1111-1111")
							.phoneCk("동의")
							.email("xx@naver.com")
							.emailCk("동의")
							.role("ROLE_USER")
							.regDate(dateTime)
							.modDate(dateTime)
							.build();
	
		for (int i = 1; i < 30; i++) {
			
			dtoTest.setId("testUser"+i);
			
			memberService.register(dtoTest);
			
		}
		
	}
	
	@Test
	public void 회원삭제() {
		
		memberService.remove("user19");
		
	}
	
	@Test
	public void 임시댓글등록() {
		
		// 서비스에 게시글 작성 register가 없어서 임의 리파지토리로 테스트
		
		Member member = Member.builder().id("user19").build();
		Qna board = Qna.builder().no(1).build();
		
		Comment comment = Comment.builder()
								.id(member)
								.qna(board)
								.content("가나다라내용내용")
								.build();
		
		commentRepository.save(comment);
		
	}
	
	@Test
	public void 임시qna등록() {
		
		QnaDTO qnaDTO = QnaDTO.builder()
								.title("1번")
								.content("내용")
								.id("user19")
								.category("추리")
								.answer(false)
								.build();
		
		qnaService.register(qnaDTO);
		
	}
	
	@Test
	public void 테스트회원등록() {
		
		LocalDateTime dateTime = LocalDateTime.now();
		
		MemberDTO dto1 = MemberDTO.builder()
							.id("testLoginIve")
							.password("11111111!")
							.name("이브=노엘")
							.birthday("001225")
							.phone("010-1111-1111")
							.phoneCk("동의")
							.email("xx@naver.com")
							.emailCk("동의")
							.role("ROLE_ADMIN")
							.build();
		
		memberService.register(dto1);
	}
	

}
