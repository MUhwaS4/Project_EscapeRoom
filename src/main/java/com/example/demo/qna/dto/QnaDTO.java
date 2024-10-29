package com.example.demo.qna.dto;

import java.time.LocalDateTime;

import com.example.demo.entity.BaseEntity;
import com.example.demo.member.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QnaDTO extends BaseEntity {
   
   int no;
   String title;
   String content;
   String id;
   String category;
   Boolean answer;
   LocalDateTime regDate;
   LocalDateTime modDate;
   
   boolean privatePost;
   String password;
   
   MemberDTO member;

}





