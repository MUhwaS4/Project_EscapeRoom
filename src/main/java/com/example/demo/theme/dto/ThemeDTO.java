package com.example.demo.theme.dto;


import org.springframework.web.multipart.MultipartFile;

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
public class ThemeDTO {

	String theme; //테마명
	
	String spotName; //지점이름

	String genre; //장르
	
	int level; //난이도
	
	int limitedTime; //제한시간
	
	int minPeople; //최소인원
	
	int maxPeople; //최대인원
	
	int lockRatio; //자물쇠비율
	
	int plantRatio; //장치비율
	
	String sysnopsis; //시놉시스
	
	MultipartFile uploadFile; // 파일 스트림
	
	String imgPath; //이미지경로
	
	String reservationTimes; // 사용자가 입력한 예약 가능한 시간들 (예: "10:30, 12:30, 14:30")
	
}