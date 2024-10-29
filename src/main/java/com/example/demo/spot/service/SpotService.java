package com.example.demo.spot.service;

import java.util.List;

import com.example.demo.spot.dto.SpotDTO;
import com.example.demo.spot.entity.Spot;

public interface SpotService {
	 List<SpotDTO> getSpotList();
	
	// DTO를 엔티티로 변환하는 메서드
	default Spot dtoToEntity(SpotDTO dto) {
	    Spot entity = Spot.builder()
	                    .spotName(dto.getSpotName())
	                    .address(dto.getAddress())
	                    .tel(dto.getTel())
	                    .latitude(dto.getLatitude())
	                    .longitude(dto.getLongitude())
	                    .build();
	    return entity;
	}

	// 엔티티를 DTO로 변환하는 메서드
	default SpotDTO entityToDto(Spot entity) {
	    // Theme 엔티티를 ThemeDTO로 변환
	    SpotDTO dto = SpotDTO.builder()
	    					.spotName(entity.getSpotName())
	    					.address(entity.getAddress())
	    					.tel(entity.getTel())
	    					.latitude(entity.getLatitude())
	    					.longitude(entity.getLongitude())
					        .build();

	    return dto;
	}
}
