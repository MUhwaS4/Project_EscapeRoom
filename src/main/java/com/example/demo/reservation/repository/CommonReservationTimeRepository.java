package com.example.demo.reservation.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.member.entity.Member;
import com.example.demo.reservation.entity.CommonReservationTime;
import com.example.demo.reservation.entity.Reservation;
import com.example.demo.spot.entity.Spot;
import com.example.demo.theme.entity.Theme;

public interface CommonReservationTimeRepository  extends JpaRepository<CommonReservationTime, Integer> {

	@Query(value =
			"SELECT crt.reservation_time AS reservationTime, crt.use_yn AS useYn, t.theme " +
            "FROM tb_common_reservation_time crt " +
            "JOIN tb_theme t ON crt.theme_theme = t.id " +
            "WHERE t.theme = :theme", nativeQuery = true)
	List<Map<String, String>> getThemeReservationTime(@Param("theme") String theme);
	
	List<CommonReservationTime> findByTheme(Theme theme);
	
	  // 예약 시간과 테마 조합으로 존재 여부 확인
    Optional<CommonReservationTime> findByReservationTimeAndTheme(String reservationTime, Theme theme);
	
}