package com.example.demo.theme.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.theme.entity.Theme;
@Repository
public interface ThemeRepository extends JpaRepository<Theme, String> {
	
	//지점을 기준으로 테마목록 조회
	List<Theme> findBySpot_SpotName(String spotName);
	
	// 테마명(theme)을 기준으로 Theme 조회(예약 dummy data 때문에 작성)
    Optional<Theme> findByTheme(String theme);
    
}
