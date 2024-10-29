package com.example.demo.comment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.comment.dto.CommentDTO;
import com.example.demo.comment.entity.Comment;
import com.example.demo.comment.repository.CommentRepository;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.qna.entity.Qna;
import com.example.demo.qna.repository.QnaRepository;

import jakarta.transaction.Transactional;

@Service
public class CommentServiceImpl implements CommentService {
   
	@Autowired
	CommentRepository commentRepository;

	@Autowired
	QnaRepository qnarepository;

	@Autowired
	MemberRepository memberRepository;

    
	@Override
	public List<CommentDTO> getListByBoardNo(int boardNo) {
       
		Qna qna = Qna.builder().no(boardNo).build();
		List<Comment> entityList = commentRepository.findByQna(qna);
		List<CommentDTO> dtoList = new ArrayList<>();
      
		for (Comment entity : entityList) {
         
			CommentDTO dto = entityToDto(entity);
			dtoList.add(dto);
         
		}

		return dtoList;
      
	}

    
	@Override
	public int register(CommentDTO dto) {
      
		Comment entity = dtoToEntity(dto);
		commentRepository.save(entity);

		return entity.getCommentNo();
      
	}

   
	@Override
	public boolean remove(int no) {
      
		Optional<Comment> comment = commentRepository.findById(no);

		if(comment.isEmpty()) {
         
			return false;
         
		}

		commentRepository.deleteById(no);
      
		// 댓글이 달린 게시글 가져오기
		Qna qna = comment.get().getQna();
       
		// 해당 게시글에 남은 댓글이 있는지 확인
		List<Comment> remainingComments = commentRepository.findByQna(qna);
       
		// 남은 댓글이 없다면 answer 값을 false로 변경
		if (remainingComments.isEmpty()) {
			qna.setAnswer(false);
			qnarepository.save(qna);  // 업데이트된 Qna 저장
		}
      
		return true;
      
	}
   
   
	@Transactional
	@Override
	public void addComment(CommentDTO commentDTO) {
      
		// 댓글 저장 로직
		Comment comment = dtoToEntity(commentDTO);
		commentRepository.save(comment);

		// 댓글을 달린 Qna 엔티티를 가져와서 answer를 true로 변경
		Qna qna = qnarepository.findByNo(commentDTO.getBoardNo())
                              .orElseThrow(() -> new IllegalArgumentException("Qna not found"));
       
		qna.setAnswer(true);  // 댓글이 달리면 answer를 true로 변경
		qnarepository.save(qna);  // 업데이트된 Qna 저장
       
		System.out.println("Qna answer updated to true for post: " + qna.getNo());
      
	}

}






