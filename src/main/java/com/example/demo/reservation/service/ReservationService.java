package com.example.demo.reservation.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.reservation.dto.ReservationDTO;
import com.example.demo.reservation.entity.Reservation;
import com.example.demo.reservation.repository.ReservationRepository;
import com.example.demo.spot.entity.Spot;
import com.example.demo.spot.repository.SpotRepository;
import com.example.demo.theme.entity.Theme;
import com.example.demo.theme.repository.ThemeRepository;

public interface ReservationService {
	
	// 예약 생성
    ReservationDTO createReservation(ReservationDTO reservationDTO);
    
    // 예약 조회 (예약 번호로 조회)
    ReservationDTO getReservationById(int no);
    
    // 모든 예약 조회
    List<ReservationDTO> getAllReservations();
    
    // 예약 수정
    ReservationDTO updateReservation(int no, ReservationDTO reservationDTO);
    
    // 예약 삭제 (취소)
    void deleteReservation(int no);
    
    // entity 변환
    default Reservation dtoToEntity(ReservationDTO dto)  {
    	
    	Member member = Member.builder().id(dto.getMemberId()).build();

    	Theme theme = Theme.builder().theme(dto.getTheme()).build();
    	
    	Spot spot = Spot.builder().spotName(dto.getSpot()).build();
        

        Reservation entity = Reservation.builder()
						            .member(member) // 예약자의 회원 정보
						            .theme(theme) // 예약된 테마
						            .spotName(spot) // 예약된 지점
						            .reservationDate(LocalDate.now()) // 현재 예약 날짜
						            .reservationTime("1040") // 현재 예약 시간
						            .people(dto.getPeople()) // 예약 인원
						            .amount(dto.getAmount()) // 결제 금액
						            .build();
		return entity;
    }

    
    // DTO 변환
    default ReservationDTO convertToDTO(Reservation reservation) {
    	ReservationDTO dto =  ReservationDTO.builder()
					                .no(reservation.getNo())  // 예약 번호
					                .memberId(reservation.getMember().getId())  // 회원 ID
					                .theme(reservation.getTheme().getTheme())  // 테마
					                .spot(reservation.getSpotName().getSpotName())  // 지점 이름을 Spot 객체에서 추출
					                .reservationName(reservation.getMember().getName())  // 예약자 이름
					                .reservationDate(reservation.getReservationDate())  // 예약 날짜
					                .reservationTime(reservation.getReservationTime())  // 예약 시간
					                .people(reservation.getPeople())  // 인원
					                .amount(reservation.getAmount())  // 결제 금액
					                .build();
        
    	return dto;
        
    }

	/**
	 * @param data
	 */
	void insertResrvationInfo(Map<String, String> data, String id);


	/**
	 * @param data 
	 * @brief
	 */
	List<Map<String, String>> getReservationInventoryList(Map<String, String> data);

	/**
	 * @param data 
	 */
	List<HashMap<String, String>> getThemeList(Map<String, String> data);

	/**
	 * @param data
	 */
	List<Map<String, String>> getSpotList(Map<String, String> data);
	
	
	/**
	 * @param data
	 */
	
	
	 // 지점별 예약 목록 조회
    List<Map<String, String>> getReservationInventoryBySpot(String spot);

	
	
}