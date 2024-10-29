package com.example.demo.theme.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.reservation.entity.CommonReservationTime;
import com.example.demo.reservation.repository.CommonReservationTimeRepository;
import com.example.demo.spot.entity.Spot;
import com.example.demo.theme.dto.ThemeDTO;
import com.example.demo.theme.entity.Theme;


public interface ThemeService {
	
		//테마 목록조회 메소드
		List<ThemeDTO> getListBySpot(String spotName);
		
		// DTO를 엔티티로 변환하는 메서드
		Theme dtoToEntity(ThemeDTO dto);
		
		// 엔티티를 DTO로 변환하는 메서드
		ThemeDTO entityToDto(Theme entity);
		
		//테마 등록 메소드
		String themeRegister(ThemeDTO dto);
		
		//테마 상세조회 메소드
		ThemeDTO read(String theme);
		
		//테마 전체조회 메소드
		List<ThemeDTO> allRead();
		
		//테마 수정 메소드
		public void modify(ThemeDTO dto) ;
		
		// 테마 삭제 메소드
		void remove(String theme);

}
