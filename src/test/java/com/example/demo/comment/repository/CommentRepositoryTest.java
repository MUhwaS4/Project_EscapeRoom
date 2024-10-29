package com.example.demo.comment.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.comment.entity.Comment;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.qna.entity.Qna;
import com.example.demo.qna.repository.QnaRepository;

@SpringBootTest
public class CommentRepositoryTest {
	
    @Autowired
	CommentRepository commentRepository;

    @Autowired
	QnaRepository qnaRepository;

    @Autowired
	MemberRepository memberRepository;

    
    @Test
	public void 댓글등록() {
    	
		// 테이블에 있는 회원
		Member member = Member.builder().id("user1").build();
		// 테이블에 있는 게시물
		Qna qna  = Qna.builder().no(1).build();
		Comment comment = new Comment(0,qna,"댓글입니다",member);
		
		commentRepository.save(comment);	
		
	}

    
	@Test
	public void 댓글목록조회() {
		
		List<Comment> list = commentRepository.findAll();
		
		for(Comment comment : list) {
			
			System.out.println(comment);
			
		}
		
	}

	
	@Test
	public void 댓글단건조회() {
		
		Optional<Comment> result = commentRepository.findById(1);
		
		if(result.isPresent()) {
			
			Comment comment = result.get();
			
			System.out.println(comment);
			
		}
		
	}
	
	
	@Test
	public void 댓글수정() {
		
		// 기존 댓글 조회
		Optional<Comment> result = commentRepository.findById(1);
		Comment comment = result.get();
		// 일부 내용 변경
		comment.setContent("내용이수정되었습니다~");
		
		// 댓글 수정
		commentRepository.save(comment);
		
	}
	
	
	@Test
	public void 댓글삭제() {
		
		qnaRepository.deleteById(1);
		
	}

	
	@Test
	public void 게시물별_댓글조회() {
		
		Qna qna = Qna.builder().no(1).build();
		List<Comment> list = commentRepository.findByQna(qna);
		
		for(Comment comment : list) {
			
			System.out.println(comment);
			
		}
		
	}

	
	@Test
	public void 게시물별_댓글삭제() {
		
		Qna qna = Qna.builder().no(1).build();
		commentRepository.deleteByQna(qna);
		
	}

}






