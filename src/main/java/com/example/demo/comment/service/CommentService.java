package com.example.demo.comment.service;

import java.util.List;

import com.example.demo.comment.dto.CommentDTO;
import com.example.demo.comment.entity.Comment;
import com.example.demo.member.entity.Member;
import com.example.demo.qna.entity.Qna;

public interface CommentService {
   
	List<CommentDTO> getListByBoardNo(int boardNo);

	int register(CommentDTO dto);

	boolean remove(int no);

	default Comment dtoToEntity(CommentDTO dto) {

		Member member = Member.builder().id(dto.getId()).build();

		Qna qna = Qna.builder().no(dto.getBoardNo()).build();

		Comment entity = Comment.builder()
								.commentNo(dto.getCommentNo())
								.qna(qna)
								.content(dto.getContent())
								.id(member)
								.build();
      
		return entity;
      
	}

   
	default CommentDTO entityToDto(Comment entity) {

		CommentDTO dto = CommentDTO.builder()
									.commentNo(entity.getCommentNo())
									.boardNo(entity.getQna().getNo())
									.content(entity.getContent())
									.id(entity.getId().getId())
									.regDate(entity.getRegDate()) 
									.modDate(entity.getModDate())
									.answer(entity.getQna().isAnswer()) 
									.build();

		return dto;
      
	}

	void addComment(CommentDTO commentDTO);

}





