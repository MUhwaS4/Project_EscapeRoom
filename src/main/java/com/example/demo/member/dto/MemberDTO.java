package com.example.demo.member.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class MemberDTO {

	String id; //아이디
	
	String password; //비밀번호
	
	String name; //이름
	
	String birthday; //이름
	
	String phone; //연락처
	
	String phoneCk; //SMS수신여부
	
	String email; //이메일
	
	String emailCk; //SMS수신여부
	
	String role; //사용자등급
	
	LocalDateTime regDate;  //등록일
	
	LocalDateTime modDate; //수정일
}