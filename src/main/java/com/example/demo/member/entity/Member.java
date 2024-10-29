package com.example.demo.member.entity;

import com.example.demo.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "MEMBER")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member extends BaseEntity {
	@Id
	@Column(length = 50)
	String id; //아이디
	
	@Column(length = 255, nullable = false)
	String password; //비밀번호
	
	@Column(length = 20, nullable = false)
	String name; //이름
	
	@Column(length = 15)
	String birthday; //생일
	
	@Column(length = 30, nullable = false)
	String phone; //연락처
	
	@Column(length = 15)
	String phoneCk; //SMS수신여부
	
	@Column(length = 255)
	String email; //이메일
	
	@Column(length = 15)
	String emailCk; //SMS수신여부
	
	@Column(length = 100, nullable = false)
	String role; //사용자등급
}
