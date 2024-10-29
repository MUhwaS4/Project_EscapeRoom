package com.example.demo.event.service;

import java.util.List;

import com.example.demo.event.dto.EventDTO;
import com.example.demo.event.entity.Event;
import com.example.demo.spot.entity.Spot;

public interface EventService {
	
	// 이벤트 등록
	void writeEvent(EventDTO eventDTO);
	
	// 이벤트 단건 조회
	EventDTO read(String id);
	
	// 이벤트 목록 조회
	List<EventDTO> getList();
	
	default EventDTO entityToDto(Event entity) {
		
		// entity.getSpot()는 Spot 객체를 반환합니다.
	    Spot spot = entity.getSpot(); // Spot 엔티티에서 spot 객체를 바로 사용

	    // Spot의 spotName을 사용해야 하므로 getSpotName()을 호출합니다.
	    EventDTO dto = EventDTO.builder()
	    						.memo(entity.getMemo())
	                            .spot(spot.getSpotName())  // spotName을 String으로 가져옴
	                            .title(entity.getTitle())
	                            .content(entity.getContent())
	                            .build();
	    
	    return dto;
		
	}
	
	default Event dtoToEntity(EventDTO dto) {
		
		Spot spot = Spot.builder().spotName(dto.getSpot()).build();
		
		Event entity = Event.builder()
							.memo(dto.getMemo())
							.spot(spot)
							.title(dto.getTitle())
							.content(dto.getContent())
							.build();
		
		return entity;
		
	}

}
