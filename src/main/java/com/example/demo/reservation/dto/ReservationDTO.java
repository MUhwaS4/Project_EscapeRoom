package com.example.demo.reservation.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.demo.member.entity.Member;
import com.example.demo.spot.entity.Spot;
import com.example.demo.theme.entity.Theme;
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

public class ReservationDTO {

	int no; // 예약 번호
	
	String memberId; // 회원 아이디 
	
	String theme; // 테마명 
	
	String spot; // 지점 이름 
	
	String reservationName; // 예약자 이름 
	
	LocalDate reservationDate; // 예약 날짜
	
	String reservationTime; // 예약 시간
	
	int people; // 인원
	
	int amount; // 결제 금액

	
}