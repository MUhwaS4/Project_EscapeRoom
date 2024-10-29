package com.example.demo.qna.entity;

import java.util.List;

import com.example.demo.comment.converter.BooleanToBitConverter;
import com.example.demo.comment.entity.Comment;
import com.example.demo.entity.BaseEntity;
import com.example.demo.member.entity.Member;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "QNA")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Qna extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int no; // 게시글 번호

    @ManyToOne
    @JoinColumn(name = "id")
    private Member id; // 작성자


    @Column(length = 100, nullable = false)
    String title; // 제목

    @Column(length = 1500, nullable = false)
    String content; // 내용

    @Builder.Default
    @Convert(converter = BooleanToBitConverter.class)
    @Column(nullable = true)
   Boolean answer = false;; // 답변 여부
   
    @Column(nullable = true)
    private boolean privatePost; // 비공개

    @Column(nullable = true)
    private String password; // 비밀번호
   
   
    @OneToMany(mappedBy = "qna", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments; // 댓글 목록
   
    // Getter for answer
    public boolean isAnswer() {
        return answer;
    }

    // Setter for answer
    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
    
}







