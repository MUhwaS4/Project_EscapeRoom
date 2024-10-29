package com.example.demo.spot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.spot.entity.Spot;


public interface SpotRepository extends JpaRepository<Spot, String>{
	
	 // 지점명(spotName)을 기준으로 Spot 조회(예약 dummy data 때문에 작성)
    Optional<Spot> findBySpotName(String spotName);
}
