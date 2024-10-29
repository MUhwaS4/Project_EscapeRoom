package com.example.demo.reservation.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.reservation.dto.ReservationDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class ReservationServiceImplTest {

    @Autowired
    ReservationService reservationService;

    private ReservationDTO reservationDTO;

    @BeforeEach
    void 초기설정() {
        reservationDTO = ReservationDTO.builder()
							                .memberId("urse1")
							                .theme("테마명1")
							                .spot("서면")
							                .reservationDate(LocalDate.now())
							                .reservationTime("1040")
							                .people(2)
							                .amount(44000)
							                .build();
    }

    @Test
    void 예약생성_테스트() {
        ReservationDTO savedReservation = reservationService.createReservation(reservationDTO);

        assertNotNull(savedReservation);
        assertEquals("테마명1", savedReservation.getTheme());
        assertEquals("urse1", savedReservation.getMemberId());
    }

    @Test
    void 예약조회_테스트() {
        // 먼저 예약을 생성하고 테스트 진행
        ReservationDTO savedReservation = reservationService.createReservation(reservationDTO);
        ReservationDTO foundReservation = reservationService.getReservationById(savedReservation.getNo());

        assertNotNull(foundReservation);
        assertEquals(savedReservation.getNo(), foundReservation.getNo());
        assertEquals("테마명1", foundReservation.getTheme());
    }

    @Test
    void 모든예약조회_테스트() {
        // 예약 생성
        reservationService.createReservation(reservationDTO);

        List<ReservationDTO> reservations = reservationService.getAllReservations();

        assertNotNull(reservations);
        assertFalse(reservations.isEmpty());
    }

    @Test
    void 예약수정_테스트() {
        // 먼저 예약을 생성하고 수정 테스트 진행
        ReservationDTO savedReservation = reservationService.createReservation(reservationDTO);
        
        ReservationDTO updateDTO = ReservationDTO.builder()
									                .no(savedReservation.getNo())
									                .memberId(savedReservation.getMemberId())
									                .theme("테마명2")
									                .spot("홍대")
									                .reservationDate(LocalDate.now())
									                .reservationTime("1040")
									                .people(4)
									                .amount(88000)
									                .build();

        ReservationDTO updatedReservation = reservationService.updateReservation(savedReservation.getNo(), updateDTO);

        assertNotNull(updatedReservation);
        assertEquals(4, updatedReservation.getPeople());
        assertEquals(88000, updatedReservation.getAmount());
        assertEquals("테마명2", updatedReservation.getTheme());
    }

    @Test
    void 예약삭제_테스트() {
        // 먼저 예약을 생성하고 삭제 테스트 진행
        ReservationDTO savedReservation = reservationService.createReservation(reservationDTO);

        reservationService.deleteReservation(savedReservation.getNo());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reservationService.getReservationById(savedReservation.getNo());
        });

        String expectedMessage = "해당 예약을 찾을 수 없습니다.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
