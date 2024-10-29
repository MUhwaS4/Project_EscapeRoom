package com.example.demo.comment.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.comment.dto.CommentDTO;
import com.example.demo.comment.repository.CommentRepository;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.qna.repository.QnaRepository;

@SpringBootTest
public class CommentServiceTest {
	
    @Autowired
	CommentService commentService;

    @Autowired
	CommentRepository commentRepository;

    @Autowired
	QnaRepository qnaRepository;

    @Autowired
	MemberRepository memberRepository;

    
	@Test
	public void 댓글등록() {

		// 테이블에 있는 게시물번호와 유저아이디 사용해야함
		CommentDTO dto = CommentDTO.builder()
									.content("댓글")
									.boardNo(2)
									.id("test")
									.build();

		commentService.register(dto);
		
	}

	
	@Test
	public void 게시물별댓글조회() {
		
		List<CommentDTO> list = commentService.getListByBoardNo(3);
		
		for(CommentDTO dto : list) {
			
			System.out.println(dto);
			
		}
		
	}

	
	@Test
	public void 댓글삭제() {
		
		commentService.remove(1);
		
	}

}





