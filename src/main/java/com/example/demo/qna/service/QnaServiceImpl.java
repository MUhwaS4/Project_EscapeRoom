package com.example.demo.qna.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.qna.dto.QnaDTO;
import com.example.demo.qna.entity.Qna;
import com.example.demo.qna.repository.QnaRepository;

@Service
public class QnaServiceImpl implements QnaService {
   
   @Autowired
   QnaRepository qnaRepository;

   @Autowired
   MemberRepository memberRepository;
   
   
   @Override
   public int register(QnaDTO dto) {
       
      Member writer = memberRepository.findById(dto.getMember().getId())
                              .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + dto.getMember().getId()));
       
      Qna entity = dtoToEntity(dto);
      
      if (dto.isPrivatePost() && dto.getPassword() != null && !dto.getPassword().isEmpty()) {
           entity.setPrivatePost(true);
           entity.setPassword(dto.getPassword());
       } else {
           entity.setPrivatePost(false);
       }
      
      qnaRepository.save(entity);
      int newNo = entity.getNo();

      return newNo;
       
   }
   
   
   @Override
   public void writeQna(QnaDTO qnaDTO, String writerId) {

      Member writer = memberRepository.findById(writerId)
                                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + writerId));
        
      Qna qna = Qna.builder()
               .title(qnaDTO.getTitle())
               .content(qnaDTO.getContent())
               .answer(false)
               .id(writer)
               .privatePost(qnaDTO.isPrivatePost())
               .password(qnaDTO.getPassword())
               .build();

      qnaRepository.save(qna);
      
   }
   
   
   @Override
   public QnaDTO read(int no) {
       Optional<Qna> result = qnaRepository.findByNo(no);
       if (result.isPresent()) {
           return entityToDto(result.get());
       } else {
           return null;
       }
   }

   
   
   @Override
   public Page<QnaDTO> getList(int pageNumber) {

      int pageNum = (pageNumber == 0) ? 0 : pageNumber - 1;
      Pageable pageable = PageRequest.of(pageNum, 10, Sort.by("no").descending());
      Page<Qna> entityPage = qnaRepository.findAll(pageable);
      Page<QnaDTO> dtoPage = entityPage.map(entity -> entityToDto(entity));

      return dtoPage;
      
   }
   
   
   // 사용자가 입력한 비밀번호 검증
   public boolean checkPassword(QnaDTO qnaDTO, String rawPassword) {

      return rawPassword.equals(qnaDTO.getPassword());
   
   }
   
   
   @Override
   public void modify(QnaDTO dto) {

       Optional<Qna> result = qnaRepository.findByNo(dto.getNo());

       if (result.isPresent()) {

           Qna entity = result.get();

           if (dto.getTitle() != null) entity.setTitle(dto.getTitle());
           if (dto.getContent() != null) entity.setContent(dto.getContent());
           
           entity.setPrivatePost(dto.isPrivatePost());
           entity.setPassword(dto.isPrivatePost() ? dto.getPassword() : null);

           qnaRepository.save(entity);

       }
   }

   
   
   @Override
   public void remove(int no) {
         
      qnaRepository.deleteById(no);
         
   }


   @Override
   public Page<QnaDTO> searchList(int pageNumber, String category, String keyword) {
      
       Pageable pageable = PageRequest.of(pageNumber, 10, Sort.by("no").descending());
          Page<Qna> entityPage;

          switch (category) {
          
              case "title":
                  entityPage = qnaRepository.findByTitleContaining(keyword, pageable);
                  break;
              case "content":
                  entityPage = qnaRepository.findByContentContaining(keyword, pageable);
                  break;
              case "id":
                  entityPage = qnaRepository.findByIdContaining(keyword, pageable);
                  break;
              case "all":
              default:
                  entityPage = qnaRepository.findByAllContaining(keyword, pageable);
                  break;
                  
          }

      return entityPage.map(this::entityToDto);
          
   }


   public String getWriterId(QnaDTO qnaDTO) {
       return qnaRepository.findById(qnaDTO.getNo())
               .map(qna -> qna.getId() != null ? qna.getId().getId() : null)
               .orElse(null);
   }


   public void setWriter(QnaDTO qnaDTO, String writerId) {
       if (qnaDTO.getMember() == null) {
           qnaDTO.setMember(new MemberDTO());
       }
       qnaDTO.getMember().setId(writerId);
   }


   @Override
   public String getWriterId(int no) {
       Qna qna = qnaRepository.findById(no).orElse(null);
        return qna != null && qna.getId() != null ? qna.getId().getId() : null;
   }
   
   
}





