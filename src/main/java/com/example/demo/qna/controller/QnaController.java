package com.example.demo.qna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.service.MemberService;
import com.example.demo.qna.dto.QnaDTO;
import com.example.demo.qna.service.QnaService;

@Controller
@RequestMapping("/qna")
public class QnaController {
   
   @Autowired
   QnaService qnaService;
   
   @Autowired
   MemberService memberService;
   
   
   // 게시글 목록
   @GetMapping("/list")
   public void list(@RequestParam(defaultValue = "0", name = "page") int page,
                     @RequestParam(defaultValue = "all", name = "category") String category,
                     @RequestParam(defaultValue = "", name = "keyword") String keyword,
                     Model model) {
      
      Page<QnaDTO> list;

       if (keyword.isEmpty()) {
           list = qnaService.getList(page);
       } else {
           list = qnaService.searchList(page, category, keyword);
       }

       model.addAttribute("list", list);
       model.addAttribute("category", category);
       model.addAttribute("keyword", keyword);
       
   }
   
   
   // 등록화면
    @GetMapping("/register")
    public String register(Model model) {
       
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            return "redirect:/escapelogin";
        }
        
        String userId = authentication.getName();
        MemberDTO member = memberService.read(userId);
        
        if (member == null) {
            return "redirect:/escapelogin";
        }
        
        String writerName = member.getName();

        model.addAttribute("writerName", writerName);
        
        return "/qna/register";
        
    }

    
    // 등록
    @PostMapping("/register")
    public String registerPost(QnaDTO dto, RedirectAttributes redirectAttributes) {
       
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String id = authentication.getName();
       
       qnaService.setWriter(dto, id);
       
       if (!dto.isPrivatePost()) {
           dto.setPrivatePost(false);
       }
       
       int no = qnaService.register(dto);
       redirectAttributes.addFlashAttribute("msg", no);
       
       return "redirect:/qna/list";
       
    }


    
    // 상세화면
    @GetMapping("/read")
    public String read(@RequestParam(name = "no") int no,
                       @RequestParam(defaultValue = "0", name = "page") int page,
                       @RequestParam(value = "password", required = false) String password,
                       Model model) {
        
        QnaDTO dto = qnaService.read(no);
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = authentication.getName();
        
        String writerId = qnaService.getWriterId(no);
        boolean isWriter = currentUserId.equals(writerId);
        
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        
        if (dto.isPrivatePost() && !isWriter && !isAdmin) {
            if (password == null || !password.equals(dto.getPassword())) {
                model.addAttribute("passwordRequired", true);
                model.addAttribute("dto", dto);
                model.addAttribute("page", page);
                return "/qna/read";
            }
        }

        model.addAttribute("isWriter", isWriter);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("dto", dto);
        model.addAttribute("page", page);
        
        return "/qna/read";
        
    }

    
    @GetMapping("/modify")
    public String modify(@RequestParam(name = "no") int no, Model model, RedirectAttributes redirectAttributes) {
        QnaDTO dto = qnaService.read(no);
        
        if (dto == null) {
            redirectAttributes.addFlashAttribute("error", "존재하지 않는 게시글입니다.");
            return "redirect:/qna/list";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = authentication.getName();

        String writerId = qnaService.getWriterId(no);
        if (writerId == null || (!writerId.equals(currentUserId) && !authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN")))) {
            redirectAttributes.addFlashAttribute("error", "수정 권한이 없습니다.");
            return "redirect:/qna/list";
        }

        model.addAttribute("dto", dto);
        return "/qna/modify";
    }


    
    // 수정
    @PostMapping("/modify")
    public String modifyPost(QnaDTO dto, RedirectAttributes redirectAttributes) {
        // 비공개 설정에 따른 비밀번호 처리
        if (!dto.isPrivatePost()) {
            dto.setPassword(null); // 공개 글일 경우 비밀번호 제거
        } else if (dto.getPassword() == null || dto.getPassword().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "비공개 설정 시 비밀번호가 필요합니다.");
            return "redirect:/qna/modify?no=" + dto.getNo();
        }

        qnaService.modify(dto);
        redirectAttributes.addAttribute("no", dto.getNo());

        return "redirect:/qna/read?no=" + dto.getNo();
    }

    
    @PostMapping("/remove")
    public String removePost(@RequestParam("no") int no, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = authentication.getName();

        String writerId = qnaService.getWriterId(no);

        if (writerId == null || (!writerId.equals(currentUserId) && !authentication.getAuthorities().stream()
               .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN")))) {
            redirectAttributes.addFlashAttribute("error", "삭제 권한이 없습니다.");
            return "redirect:/qna/list";
        }

        qnaService.remove(no);
        redirectAttributes.addFlashAttribute("msg", "게시글이 삭제되었습니다.");

        return "redirect:/qna/list";
    }


}






