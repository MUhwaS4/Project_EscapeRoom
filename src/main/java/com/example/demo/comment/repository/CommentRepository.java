package com.example.demo.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.comment.entity.Comment;
import com.example.demo.member.entity.Member;
import com.example.demo.qna.entity.Qna;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

   List<Comment> findByQna(Qna qna);
   
   // 게시물을 기준으로 댓글을 모두 삭제
   void deleteByQna(Qna qna);
   
   List<Comment> findById(Member member); // 작성자 id로 댓글 조회
   
   // 특정 QnA 게시글에 달린 댓글 수 세기
    int countByQna(Qna qna);

}






