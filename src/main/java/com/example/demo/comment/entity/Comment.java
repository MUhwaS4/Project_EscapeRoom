package com.example.demo.comment.entity;

import com.example.demo.entity.BaseEntity;
import com.example.demo.member.entity.Member;
import com.example.demo.qna.entity.Qna;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "COMMENT")
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment extends BaseEntity {
   @Id
   @GeneratedValue (strategy = GenerationType.IDENTITY)
   int commentNo; //댓글번호

   @ManyToOne
   Qna qna; //게시글
   
   @Column(length = 1500, nullable = false)
   String content; //댓글내용
   
   @ManyToOne
   Member id; //작성자
   
}






