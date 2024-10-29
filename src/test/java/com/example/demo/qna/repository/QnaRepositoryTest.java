package com.example.demo.qna.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.member.entity.Member;
import com.example.demo.qna.entity.Qna;
import com.example.demo.qna.service.QnaService;

@SpringBootTest
public class QnaRepositoryTest {
	
	@Autowired
	QnaRepository qnaRepository;

	@Autowired
	QnaService qnaService;

	
	@Test
	void 게시물등록() {
		
		// 테이블에 존재하는 회원 정보로 엔티티 생성 (PK만 필요)
		Member member = Member.builder().id("user1").build();

		// 작성자 필드에 회원 정보 입력
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
	public void 게시물전체조회() {

		List<Qna> qnaList = qnaRepository.findAll();
	    
	    for (Qna qna : qnaList) {
	        
	    	System.out.println(qna);
	        
	    }
	}
	
	
    @Test
    public void 게시물단건조회() {
    	
        Optional<Qna> optional = qnaRepository.findById(1);
        optional.ifPresent(System.out::println);
        
    }
	
	
    @Test
    public void 게시물수정() {
    	
        Optional<Qna> optional = qnaRepository.findById(1);
        
        if (optional.isPresent()) {
        
        	Qna qna = optional.get();
            qna.setTitle("수정된 제목");
            qna.setContent("수정된 내용");
            qnaRepository.save(qna);
            
        }
        
    }

    
    @Test
    public void 게시물삭제() {
    	
        qnaRepository.deleteById(1);
        
    }

}





