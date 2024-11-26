package com.example.demo.reservation.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.member.entity.Member;
import com.example.demo.reservation.entity.Reservation;
import com.example.demo.spot.entity.Spot;
import com.example.demo.theme.entity.Theme;

import jakarta.transaction.Transactional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

	/**
	 * @param amount
	 * @param people
	 * @param reservation_date
	 * @param reservation_time
	 * @param member_id
	 * @param spot_name_spot_name
	 * @param theme_theme
	 */
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO escape_reservation (amount, people, reservation_date, reservation_time, member_id, spot_name_spot_name, theme_theme) "
					+ "VALUES (:amount, :people, :reservation_date, :reservation_time, :member_id, :spot_name_spot_name, :theme_theme)", 
            nativeQuery = true)
	int insertResrvationInfo(
							  @Param("amount") int amount
							, @Param("people") int people
							, @Param("reservation_date") String reservation_date
							, @Param("reservation_time") String reservation_time
							, @Param("member_id") String member_id
							, @Param("spot_name_spot_name") String spot_name_spot_name
							, @Param("theme_theme") String theme_theme
							);

	@Query(value = "SELECT R.NO as no, M.NAME as name, R.theme_theme as theme, R.reservation_date as reservationDate, "
					+ "CONCAT(SUBSTRING(R.reservation_time, 1, 2), ' : ', SUBSTRING(R.reservation_time, 3, 2)) as reservationTime, "
					+ "R.people as people, R.spot_name_spot_name as spot "
					+ "FROM escape_reservation R, escape_member M "
					+ "WHERE R.member_id = M.id "
					+ "ORDER BY R.NO DESC", nativeQuery = true)
	List<Map<String, String>> getReservationInventoryList();

	@Query(value = "SELECT spot_name FROM bootex.escape_spot", nativeQuery = true)
	List<Map<String, String>> getSpotList();
	
	// 더미 데이터 중복 방지용 부분 추가
	boolean existsByMemberAndThemeAndSpotName(Member member, Theme theme, Spot spot);
		
	@Query(value = "SELECT R.NO AS no, "
					+ "M.NAME AS name, "
					+ "R.theme_theme AS theme, "
					+ "R.reservation_date AS reservationDate, "
					+ "CONCAT(SUBSTRING(R.reservation_time, 1, 2), ' : ', SUBSTRING(R.reservation_time, 3, 2)) AS reservationTime, "
					+ "R.people AS people, "
					+ "R.spot_name_spot_name AS spot "
					+ "FROM escape_reservation R "
					+ "JOIN `member` M ON R.member_id = M.id "
					+ "WHERE R.spot_name_spot_name = :spot " // 지점별 필터링
					+ "ORDER BY R.NO DESC",
					nativeQuery = true)
	    List<Map<String, String>> findReservationsBySpot(@Param("spot") String spot);
	
	@Query(value = "SELECT theme, genre, `level`, limited_time, lock_ratio, max_people, "
					+ "min_people, plant_ratio, sysnopsis, spot_spot_name, img_path AS imgPath "
			 		+ "FROM bootex.escape_theme "
			 		+ " WHERE spot_spot_name = :spot",
			 		nativeQuery = true)
	List<Map<String, String>> getThemeList(@Param("spot") String spot);

	/**
	 * @param theme
	 */
	@Query(value = "SELECT reservation_time AS reservationTime, use_yn AS useYn "
					+ "FROM escape_common_reservationtime "
					+ "WHERE theme = :theme",
					nativeQuery = true)

	List<Map<String, String>> getThemeReservationTime(@Param("theme") String theme);

	/**
	 * @param spot
	 * @param theme
	 * @param reservationTime
	 * @param reservationTime 
	 */
	@Query(value = "SELECT COUNT(1) "
					+ "FROM bootex.escape_reservation "
					+ "WHERE spot_name_spot_name = :spot "
					+ " AND theme_theme = :theme "
					+ " AND reservation_date = :reservationDate "
					+ " AND reservation_time = :reservationTime", 
					nativeQuery = true)
	int getReservationAvailable(@Param("spot") 			String spot
								,@Param("theme") 			String theme
								,@Param("reservationDate") 	String reservationDate
								,@Param("reservationTime") 	String reservationTime);
		
	/**
	 * @name: 서연
	 * @day : 10.23
	 * @param : 회원 아이디로 예약 조회
	 */
	List<Reservation> findByMember(Member member);

}