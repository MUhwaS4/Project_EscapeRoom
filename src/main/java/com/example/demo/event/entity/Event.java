package com.example.demo.event.entity;

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
@Table(name = "EVENT_LIST")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {
	
	@Id
	@Column(length = 255)
	String memo; // 순번(PK용)
	
	@ManyToOne()
	Spot spot; // 지점
	
	@Column(length = 255, nullable = false)
	String title; // 이벤트 제목
	
	@Column(length = 1500)
	String content; // 이벤트 내용

}
