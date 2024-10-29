package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.member.dto.CustomUser;
import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.service.MemberService;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	MemberService memberService;

	// 로그인 처리 메소드
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("login id: " + username);
		
		// 아이디로 회원 조회
		MemberDTO dto = memberService.read(username);
		
		// 회원이 있으면 로그인 성공, 없으면 실패
		if (dto != null) {
			// DTO -> 인증 객체로 변환 후 반환 (이 인증 객체를 main 화면 인증 객체 정보로 전달함)
			return new CustomUser(dto);
		} else {
			// 에러 발생시키기
			throw new UsernameNotFoundException("");
		}
		
	}

}
