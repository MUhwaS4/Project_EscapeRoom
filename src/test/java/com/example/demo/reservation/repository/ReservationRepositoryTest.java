package com.example.demo.reservation.repository;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.reservation.entity.Reservation;
import com.example.demo.spot.entity.Spot;
import com.example.demo.spot.repository.SpotRepository;
import com.example.demo.theme.entity.Theme;
import com.example.demo.theme.repository.ThemeRepository;

@SpringBootTest
public class ReservationRepositoryTest {
	
    @Autowired
    ReservationRepository repository;
    
    @Autowired
    MemberRepository memberRepository;
    
    @Autowired
    ThemeRepository themeRepository;
    
    @Autowired
    SpotRepository spotRepository;
    
    @Test
    void 리파지토리객체를가져왔는지확인() {
        System.out.println("repository = " + repository);
    }
    
    @Test
    public void 회원등록() {
        // 회원 정보 저장
        Member member = Member.builder()
					        		.id("urse1")
					                .password("1234")
					                .name("짱아")
					                .phone("010-0000-0000")
					                .phoneCk("on")
					                .email("a@gmail.com")
					                .emailCk("수신동의")
					                .role("ROLE_USER")
					                .build();
        memberRepository.save(member); // 먼저 저장
    }
    
    @Test
    public void 스팟등록() {
    	
	      // 지점 정보 저장
	      Spot spot = Spot.builder()
					        		.spotName("인천구월")
					        		.address("서울시 마포구 홍대")
					        		.tel("02-0000-0000")
					        		.build();
	      spotRepository.save(spot); // 먼저 저장
    	
    }
    
    @Test
    public void 테마등록() {
    	
        // 지점 정보 저장
	      Spot spot = Spot.builder()
					        		.spotName("인천구월")
					        		.build();
    	
      // 테마 정보 저장
      Theme theme = Theme.builder()
				        		.theme("테마 1")
				                .spot(spot) // 지점 정보 설정
				                .genre("장르명") // 장르 필드 설정
				                .level(1) // 난이도
				                .limitedTime(60) // 제한시간
				                .minPeople(1) // 최소인원
				                .maxPeople(5) // 최대인원
				                .lockRatio(50) // 자물쇠 비율
				                .plantRatio(50) // 장치 비율
				                .sysnopsis("테마 설명") // 시놉시스
				                .build();
      themeRepository.save(theme); // 먼저 저장
    	
    }
    
    @Test
    public void 예약등록() {
        
        Member member = Member.builder()
					        		.id("test")
					                .build();
        
        Theme theme = Theme.builder()
  				        		.theme("테마 1")
  				                .build();
        
        // 지점 정보 저장
	    Spot spot = Spot.builder()
					        		.spotName("인천구월")
					        		.build();
        
        // 예약 정보 저장
        Reservation reservation = Reservation.builder()
                                             .member(member) // 이미 저장된 member
                                             .theme(theme) // 이미 저장된 theme
                                             .spotName(spot) // 이미 저장된 spot
                                             .reservationDate(LocalDate.now()) // 오늘 날짜
                                             .reservationTime("1040") // 현재 시간
                                             .people(2) // 인원 수
                                             .amount(44000) // 결제 금액
                                             .build();

        repository.save(reservation); 
    }
    
    @Test
	void 예약조회() {
		
		List<Reservation> list = repository.findAll();
		
		for (Reservation reservation : list) {
			
			System.out.println(list); 
		}
    }
    
    @Test
	public void 데이터삭제() {
    	repository.deleteById(8); // 1번이 존재하는지 확인하고 delete 실행
	}

	@Test
	public void 데이터전체삭제() {
		repository.deleteAll(); // 테이블을 조회하고 모든 데이터를 삭제함
	}
    
    
}