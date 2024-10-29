package com.example.demo.reservation.dataInitializer;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.reservation.entity.Reservation;
import com.example.demo.reservation.repository.ReservationRepository;
import com.example.demo.spot.entity.Spot;
import com.example.demo.spot.repository.SpotRepository;
import com.example.demo.theme.entity.Theme;
import com.example.demo.theme.repository.ThemeRepository;

@Component
@Order(6)
public class ReservationList implements ApplicationRunner {
	
	
	private final ReservationRepository reservationRepository;
	private final MemberRepository memberRepository;
	private final ThemeRepository themeRepository;
	private final SpotRepository spotRepository;

    public ReservationList(ReservationRepository reservationRepository, 
                           MemberRepository memberRepository, 
                           ThemeRepository themeRepository,
                           SpotRepository spotRepository) {
        this.reservationRepository = reservationRepository;
        this.memberRepository = memberRepository;
        this.themeRepository = themeRepository;
        this.spotRepository = spotRepository;
    }

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		// 데이터베이스에서 이미 저장된 Member, Theme, Spot 데이터를 가져옵니다.
		Optional<Member> existingMember = memberRepository.findById("test");
		Optional<Theme> existingTheme = themeRepository.findByTheme("[프리미엄 테마]미즈몰리아와 수수께끼의 책");
		Optional<Spot> existingSpot = spotRepository.findBySpotName("홍대");
		
		// 예약이 이미 존재하는지 확인합니다.
		boolean reservationExists = reservationRepository.existsByMemberAndThemeAndSpotName(
		        existingMember.orElse(null),
		        existingTheme.orElse(null),
		        existingSpot.orElse(null)
		        );
		
		
		
		// Member, Theme, Spot 데이터가 모두 존재할 때만 예약 정보를 저장
		if (existingMember.isPresent() && existingTheme.isPresent() && existingSpot.isPresent() && !reservationExists) {
			// 예약 정보 저장
			Reservation reservation = Reservation.builder()
                                             .member(existingMember.get()) // 이미 저장된 member
                                             .theme(existingTheme.get()) // 이미 저장된 theme
                                             .spotName(existingSpot.get()) // 이미 저장된 spot
                                             .reservationDate(LocalDate.now()) // 오늘 날짜
                                             .reservationTime("1040") // 예약 시간
                                             .people(2) // 인원 수
                                             .amount(44000) // 결제 금액
                                             .build();
        
            reservationRepository.save(reservation);
            System.out.println("예약 정보가 저장되었습니다.");
		} else {
			System.out.println("Member, Theme, Spot 데이터가 존재하지 않아 예약 정보를 저장할 수 없습니다.");
		}
	}
}
	