package com.example.demo.spot.dto;

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
public class SpotDTO {

	String spotName; //지점이름
	
	String address; //주소
	
	String tel; //전화번호
	
	double latitude; //위도
	
	double longitude; //경도
}