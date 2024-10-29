package com.example.demo.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.event.dto.EventDTO;
import com.example.demo.event.service.EventService;
import com.example.demo.theme.dto.ThemeDTO;
import com.example.demo.theme.service.ThemeService;

@Controller
public class MainController {
	
	@Autowired
	EventService eventService;
	
	@Autowired
	ThemeService themeService;
	
	@GetMapping("/")
	public String main(Model model) {
		//이벤트 목록을 가져옴
		List<EventDTO> list = eventService.getList();
		model.addAttribute("list", list);
		
		//테마 목록을 가져옴
		List<ThemeDTO> themeList = themeService.allRead();
		model.addAttribute("themeList", themeList);
		
		return "/home/main";
	}
	
	@GetMapping("/reservation")
    public String handleReservation(
        @RequestParam("point") String point, 
        @RequestParam("date") String date, 
        Model model) {
        
        // 받은 파라미터를 처리하는 로직 (여기서는 모델에 추가해 뷰로 전달)
        model.addAttribute("selectedPoint", point);
        model.addAttribute("selectedDate", date);

        // 예시로, 뷰 페이지로 전달 (HTML 페이지로 이동)
        return "/reservation/list";  // reservation.html 뷰 페이지로 이동
    }
	
	// 커스텀 로그인 화면을 반환하는 메소드
	// 해당 메소드를 통하지 않으면 시큐리티에서 제공하는 템플릿을 자동으로 사용하게 됨
	@GetMapping("/escapelogin")
	public String login() {
	    return "home/login"; // 변경된 부분
	}
	
	@ModelAttribute
    public void addAttributes(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String userId = authentication.getName(); // 사용자 ID 가져오기
            model.addAttribute("userId", userId);
        }
    }

}
