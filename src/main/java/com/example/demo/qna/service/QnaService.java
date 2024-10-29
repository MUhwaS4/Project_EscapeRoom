package com.example.demo.qna.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.entity.Member;
import com.example.demo.qna.dto.QnaDTO;
import com.example.demo.qna.entity.Qna;

@Service
public interface QnaService {
   
   // 게시물 등록 메소드
   int register(QnaDTO dto);
   
   // 게시물 목록조회 메소드
   Page<QnaDTO> getList(int pageNumber);
   
   Page<QnaDTO> searchList(int pageNumber, String category, String keyword);
   
   // 게시물 상세조회 메소드
   QnaDTO read(int no);

   // 게시물 수정 메소드
   void modify(QnaDTO dto);

   // 게시물 삭제 메소드
   void remove(int no);
   
   void writeQna(QnaDTO qnaDTO, String id);
   
   // 비밀번호 검증 메소드
   boolean checkPassword(QnaDTO qnaDTO, String rawPassword);
   
   
   public String getWriterId(QnaDTO qnaDTO);

   public void setWriter(QnaDTO qnaDTO, String writerId);
   
   String getWriterId(int no);
   
   
   // dto를 엔티티로 변환하는 메소드
   default Qna dtoToEntity(QnaDTO dto) {
      
      Member member = Member.builder().id(dto.getMember().getId()).build(); 

      Qna entity = Qna.builder()
                  .no(dto.getNo())
                  .title(dto.getTitle())
                  .content(dto.getContent())
                  .id(member)
                  .privatePost(dto.isPrivatePost())
                  .password(dto.getPassword())
                  .build();
      return entity;
   }

   
   // 엔티티를 dto로 변환하는 메소드
   default QnaDTO entityToDto(Qna entity) {
      
      MemberDTO memberDTO = new MemberDTO();
       
      if (entity.getId() != null) {
         memberDTO.setId(entity.getId().getId());
         memberDTO.setName(entity.getId().getName());
      } else {
           memberDTO = null;
       }

   QnaDTO dto = QnaDTO.builder()
                  .no(entity.getNo())
                  .title(entity.getTitle())
                  .content(entity.getContent())
                  .member(memberDTO)
                  .regDate(entity.getRegDate())
                  .modDate(entity.getModDate())
                  .answer(entity.getAnswer())
                  .privatePost(entity.isPrivatePost())
                  .password(entity.getPassword())
                  .build();

      return dto;
      
   }
   
}





