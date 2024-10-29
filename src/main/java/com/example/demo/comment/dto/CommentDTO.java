package com.example.demo.comment.dto;

import java.time.LocalDateTime;

import com.example.demo.entity.BaseEntity;

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
public class CommentDTO extends BaseEntity {
   
   int commentNo;
   String id;
   int boardNo;
   String content;
   LocalDateTime regDate;
   LocalDateTime modDate;
   boolean answer;

   
    // Getter for answer
    public boolean isAnswer() {
        return answer;
    }

    // Setter for answer
    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
    
}





