package com.example.demo.member.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.service.MemberService;
import com.example.demo.reservation.dto.ReservationDTO;
import com.example.demo.reservation.repository.ReservationRepository;
import com.example.demo.reservation.service.ReservationService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	// 회원가입 화면 반환
	@GetMapping("/register")
	public String register() {
		return "/member/register";
	}
	
	// 회원 등록 메소드
	@PostMapping("/register")
	public String registerPost(MemberDTO dto, RedirectAttributes redirectAttributes) {
		
		boolean result = memberService.register(dto);
		
		if (result) {

			redirectAttributes.addFlashAttribute("msg", "환영합니다, " + dto.getName() + " 님!");
			return "redirect:/";
			
		} else {
			
			// 등록에 실패했으면 다시 회원가입 폼으로 이동하면서 에러메시지 표시
			redirectAttributes.addFlashAttribute("msg", "아이디가 중복되어 등록에 실패하였습니다");
			return "redirect:/register";
			
		}
		
	}
	
	// 아이디 중복 여부 확인
	@GetMapping("/IDCheck")
	@ResponseBody // 주소값이 아닌 글자 그대로를 반환
	public ResponseEntity<String> idCheck(@RequestParam(name = "userid") String userId) {
		
	    Optional<Member> memberOptional = memberRepository.findById(userId);

	    // 아이디가 이미 존재하면 중복이라고 응답
	    if (memberOptional.isPresent()) {
	        return ResponseEntity.ok("중복된 아이디입니다.");
	    } else {
	        return ResponseEntity.ok("사용 가능한 아이디입니다.");
	    }
	    
	}
	
	// 마이페이지(상세) 화면 반환
	@GetMapping("/mypage")
	public String myPage(@RequestParam(name = "id") String id, @RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		
		// 전달받은 파라미터로 회원 조회
		MemberDTO dto = memberService.read(id);
		
		// 조회한 회원 정보를 화면에 전달
		model.addAttribute("member", dto);
		
		// 전달받은 파라미터로 예약 목록 조회
		List<ReservationDTO> list = reservationService.getAllReservations();
		
		model.addAttribute("list", list);
		
		// 페이지 정보를 화면에 전달
		model.addAttribute("page", page);
		
		// 로그인한 유저 데이터 반환(=단건 조회)은 로그인 시큐리티 적용 후 확인
		// 페이지에 들어온 유저 ID값을 그대로 파라미터에 적용할 수 있도록
		
		return "/member/read";
	}
	
	// 회원 목록 반환
	@GetMapping("/member/list")
	public void list(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		
		Page<MemberDTO> list = memberService.getList(page);
		
		model.addAttribute("list", list);
		
	}
	
	// 회원 정보 수정 메소드
	@PostMapping("/mypage")
	public String modifyMember(@ModelAttribute("dto") MemberDTO dto, RedirectAttributes redirectAttributes, Model model) {
		
		// 전달받은 폼 데이터로 기존 게시물 수정
		memberService.modify(dto);
		
		// 상세 화면으로 이동할 때 파라미터 전달
		redirectAttributes.addAttribute("id", dto.getId());
		
		return "redirect:/mypage";
		
	}
	
	// 회원 탈퇴 메소드
	@PostMapping("/member/remove")
	public String removeMember(@RequestParam("id") String userId, RedirectAttributes redirectAttributes) {
		
		// 전달받은 파라미터로 회원 삭제
		memberService.remove(userId);

		redirectAttributes.addFlashAttribute("msg", "회원 탈퇴가 완료되었습니다. 안녕히 가세요!");
		return "redirect:/";
		
	}

}
