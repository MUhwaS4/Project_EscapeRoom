package com.example.demo.event.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.event.entity.Event;
import com.example.demo.spot.entity.Spot;

@SpringBootTest
public class EventRepositoryTest {
	
	@Autowired
	EventRepository eventRepository;
	
	@Test
	public void 빈주입() {
		
	}
	
	@Test
	public void 이벤트등록() {
		
		Spot spot1 = Spot.builder().spotName("홍대").build();
		Spot spot2 = Spot.builder().spotName("인천구월").build();
		Spot spot3 = Spot.builder().spotName("서면").build();
		
		Event event1 = Event.builder()
							.memo("인증샷")
							.spot(spot1)
							.title("인증샷과 힌트 하나의 연관성을 알아보자!")
							.content(null)
							.build();
		
		Event event2 = Event.builder()
							.memo("옵저버")
							.spot(spot2)
							.title("옵저버와 함께라면 어려운 문제도 쉽게!")
							.content(null)
							.build();
		
		Event event3 = Event.builder()
							.memo("할로윈")
							.spot(spot3)
							.title("할로윈 기념 인스타그램 인증샷으로 음료 받기!")
							.content(null)
							.build();
		
		eventRepository.save(event1);
		eventRepository.save(event2);
		eventRepository.save(event3);
	}

}
