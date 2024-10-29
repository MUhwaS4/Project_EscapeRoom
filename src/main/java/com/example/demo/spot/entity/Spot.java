package com.example.demo.spot.entity;

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
@Table(name="SPOT") //실제 테이블 이름
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Spot {
	@Id
	String spotName; //지점이름
	
	@Column(length = 200, nullable = false)
	String address; //주소
	
	@Column(length = 50, nullable = false)
	String tel; //전화번호
	
	@Column
	double latitude; //위도
	
	@Column
	double longitude; //경도
	
}
