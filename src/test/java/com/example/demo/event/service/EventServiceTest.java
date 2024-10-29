package com.example.demo.event.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.event.dto.EventDTO;
import com.example.demo.spot.entity.Spot;
import com.example.demo.spot.service.SpotService;

@SpringBootTest
public class EventServiceTest {
	
	@Autowired
	EventService eventService;
	
	@Autowired
	SpotService spotService;
	
	@Test
	public void 이벤트등록() {
		
		Spot spot = Spot.builder().spotName("홍대").build();
		
		EventDTO dto = EventDTO.builder()
                .memo("테스트2") // memo 값을 명시적으로 설정
                .spot(spot.getSpotName())
                .title("제목")
                .content("내용")
                .build();
		
		eventService.writeEvent(dto);
	}
	
	@Test
	public void 이벤트조회() {
		
		eventService.getList();
		
	}

}
