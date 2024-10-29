package com.example.demo.reservation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.member.repository.MemberRepository;
import com.example.demo.reservation.entity.CommonReservationTime;
import com.example.demo.reservation.repository.ReservationRepository;
import com.example.demo.spot.repository.SpotRepository;
import com.example.demo.theme.entity.Theme;
import com.example.demo.theme.repository.ThemeRepository;

@SpringBootTest
public class CommonReservationTimeTest {

		@Autowired
	    ReservationRepository repository;

	    @Autowired
	    ThemeRepository themeRepository;
	    
//	    @Test
//	    public void 테마별_시간등록() {
//	        // Theme 객체를 데이터베이스에서 조회하거나 생성
//	        Theme theme = themeRepository.findById(1L)
//	                .orElseGet(() -> {
//	                    Theme newTheme = new Theme();
//	                    newTheme.setName("신데렐라");
//	                    themeRepository.save(newTheme);
//	                    return newTheme;
//	                });
//
//	        // CommonReservationTime 객체 생성
//	        CommonReservationTime commonReservationTime = CommonReservationTime.builder()
//	                .reservationTime("1030")
//	                .theme(theme) // Theme 객체 설정
//	                .useYn("Y")
//	                .build();
//
//	        // 데이터베이스에 저장
//	        commonReservationTimeRepository.save(commonReservationTime);
//
//	        System.out.println("테마별 시간 등록 완료: " + commonReservationTime);
//	    }
}
