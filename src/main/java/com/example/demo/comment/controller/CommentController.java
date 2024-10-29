package com.example.demo.comment.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.comment.dto.CommentDTO;
import com.example.demo.comment.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {
   
    @Autowired
   CommentService commentService;

    
   //게시물별 댓글 목록 조회
    @ResponseBody
   @GetMapping("/list")
   public List<CommentDTO> list(@RequestParam(name = "boardNo") int boardNo) {

      List<CommentDTO> commentlist = commentService.getListByBoardNo(boardNo);

      return commentlist;
      
   }
   
   
   @PostMapping("/register")
   @ResponseBody
   public Boolean register(CommentDTO dto, Principal principal) {
      
       String id = "test";
       dto.setId(id);
       commentService.addComment(dto);
      
      return true;
      
   }
   
   
   @DeleteMapping("/remove")
   @ResponseBody
   public Boolean remove(@RequestParam(name = "commentNo") int commentNo) {

      boolean result = commentService.remove(commentNo);

      return result;
      
   }

}






