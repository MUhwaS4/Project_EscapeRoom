package com.example.demo.event.dataInitializer;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.demo.event.entity.Event;
import com.example.demo.event.repository.EventRepository;
import com.example.demo.spot.entity.Spot;

@Component
@Order(7)
public class EventListDataInitializer implements ApplicationRunner {
	
	private final EventRepository eventRepository;

    public EventListDataInitializer(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Spot spot1 = Spot.builder().spotName("홍대").build();
		Spot spot2 = Spot.builder().spotName("인천구월").build();
		Spot spot3 = Spot.builder().spotName("서면").build();
		
		eventRepository.save(new Event("인증샷", spot1, "인증샷과 힌트 하나의 연관성을 알아보자!", null));
		eventRepository.save(new Event("옵저버", spot2, "옵저버와 함께라면 어려운 문제도 쉽게!", null));
		eventRepository.save(new Event("할로윈", spot3, "할로윈 기념 인스타그램 인증샷으로 음료 받기!", null));
		
	}

}
