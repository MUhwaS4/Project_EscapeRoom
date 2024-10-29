package com.example.demo.member.service;


import org.springframework.data.domain.Page;

import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.entity.Member;

public interface MemberService {
	
	// 회원 등록
	boolean register(MemberDTO dto);
	
	// 회원 단건 조회
	MemberDTO read(String id);
	
	// 회원 목록 조회
	Page<MemberDTO> getList(int pageNumber);
//	List<MemberDTO> getList();
	
	// 아이디 중복 조회
	public boolean checkIdExists(String userId);
	
	// 회원 정보 수정 메소드
	void modify(MemberDTO dto);
	
	// 회원 삭제 메소드
	void remove(String userId);
	
	// ENTITY => DTO 변환 메소드
	default MemberDTO entityToDto(Member entity) {
		
		MemberDTO dto = MemberDTO.builder()
								.id(entity.getId())
								.password(entity.getPassword())
								.name(entity.getName())
								.birthday(entity.getBirthday())
								.phone(entity.getPhone())
								.phoneCk(entity.getPhoneCk())
								.email(entity.getEmail())
								.emailCk(entity.getEmailCk())
								.role(entity.getRole())
								.regDate(entity.getRegDate())
								.modDate(entity.getModDate())
								.build();
		
		return dto;
		
	}
	
	// DTO => ENTITY 변환 메소드
	default Member dtoToEntity(MemberDTO dto) {
		
		Member entity = Member.builder()
								.id(dto.getId())
								.password(dto.getPassword())
								.name(dto.getName())
								.birthday(dto.getBirthday())
								.phone(dto.getPhone())
								.phoneCk(dto.getPhoneCk())
								.email(dto.getEmail())
								.emailCk(dto.getEmailCk())
								.role(dto.getRole())
								.build();
		
		return entity;
		
	}

}
