package com.example.demo.qna.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.member.entity.Member;
import com.example.demo.qna.entity.Qna;

@Repository
public interface QnaRepository extends JpaRepository<Qna, Integer> {
   
   List<Qna> findById(Member id); // 작성자 ID로 QNA 게시글 조회
   
   Optional<Qna> findByNo(Integer no);  // 게시글 번호로 조회
   
   // 제목으로 검색
    Page<Qna> findByTitleContaining(String keyword, Pageable pageable);
    
    // 내용으로 검색
    Page<Qna> findByContentContaining(String keyword, Pageable pageable);
    
    // 작성자 ID로 검색
    Page<Qna> findByIdContaining(String keyword, Pageable pageable);

    // 제목, 내용, 작성자 모두 포함하는 검색
    @Query("SELECT q FROM Qna q WHERE q.title LIKE %:keyword% OR q.content LIKE %:keyword% OR q.id.id LIKE %:keyword%")
    Page<Qna> findByAllContaining(@Param("keyword") String keyword, Pageable pageable);
    
    Optional<Qna> findByNoAndPassword(int no, String password); // 비공개 글에 대해 비밀번호 검증

}
