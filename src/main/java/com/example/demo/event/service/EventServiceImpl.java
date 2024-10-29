package com.example.demo.event.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.event.dto.EventDTO;
import com.example.demo.event.entity.Event;
import com.example.demo.event.repository.EventRepository;
import com.example.demo.spot.entity.Spot;
import com.example.demo.spot.repository.SpotRepository;

@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	SpotRepository spotRepository;

	@Override
	// 이벤트 등록
	public void writeEvent(EventDTO eventDTO) {
		
		Spot spot = spotRepository.findBySpotName(eventDTO.getSpot())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 지점입니다."));
		
		Event event = Event.builder()
							.memo(eventDTO.getMemo())
							.spot(spot)
							.title(eventDTO.getTitle())
							.content(eventDTO.getContent())
							.build();
		
		eventRepository.save(event);
		
	}

	@Override
	// 이벤트 단건 조회
	public EventDTO read(String id) {
		
		Optional<Event> result = eventRepository.findById(id);
		
		if (result.isPresent()) {
			
			Event event = result.get();
			return entityToDto(event);
			
		}
		
		return null;
	}

	@Override
	// 이벤트 목록 조회
	public List<EventDTO> getList() {
		
		List<Event> result = eventRepository.findAll();
		List<EventDTO> list = new ArrayList<>();
		
		list = result.stream()
					.map(entity -> entityToDto(entity))
					.collect(Collectors.toList());
		
		return list;
	}
	
}
