package com.example.demo.theme.entity;

import com.example.demo.spot.entity.Spot;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "THEME")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Theme {
	@Id
	@Column(length = 255)
	String theme; //테마명
	
	@ManyToOne
	Spot spot; //지점명
	
	@Column(length = 20, nullable = false)
	String genre; //장르
	
	@Column(nullable = false)
	int level; //난이도
	
	@Column(nullable = false)
	int limitedTime; //제한시간
	
	@Column(nullable = false)
	int minPeople; //최소인원
	
	@Column(nullable = false)
	int maxPeople; //최대인원
	
	@Column(nullable = false)
	int lockRatio; //자물쇠비율
	
	@Column(nullable = false)
	int plantRatio; //장치비율
	
	@Column(length = 1500, nullable = false)
	String sysnopsis; //시놉시스
	
	@Column(length = 1500)
	private String imgPath; //첨부파일 이름
	
	@Column(length = 255)
	String reservationTimes; 

}
