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

/**
 * 
 */
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
	@Query(value = 
			"INSERT INTO reservation " + 
					"(" + 
					"amount" + 
					",people" + 
					",reservation_date" + 
					",reservation_time" + 
					",member_id" + 
					",spot_name_spot_name" + 
					",theme_theme         " + 
					")" + 
					"VALUES" + 
					"(" + 
					":amount" + 
					",:people" + 
					",:reservation_date" + 
					",:reservation_time" + 
					",:member_id" + 
					",:spot_name_spot_name" + 
					",:theme_theme       " + 
					")"
					, nativeQuery = true)
	int insertResrvationInfo(
							  @Param("amount") int amount
							, @Param("people") int people
							, @Param("reservation_date") String reservation_date
							, @Param("reservation_time") String reservation_time
							, @Param("member_id") String member_id
							, @Param("spot_name_spot_name") String spot_name_spot_name
							, @Param("theme_theme") String theme_theme
							);
	
//	@Query(value = ""
//	+ "SELECT R.NO as no\r\n"
//	+ "       , M.NAME as name\r\n"
//	+ "       , R.theme_theme as theme \r\n"
//	+ "       , R.reservation_date as reservationDate \r\n"
//	+ "       , R.reservation_time as reservationTime \r\n" 
//	+ "       , R.people as people\r\n"
//	+ "       , R.spot_name_spot_name as spot\r\n"
//	+ "  FROM reservation R\r\n"
//	+ "       , `member` m \r\n"
//	+ " WHERE 1 = 1 \r\n"
//	+ "   AND R.member_id = M.id\r\n"
//	+ " ORDER BY R.reservation_date DESC, R.reservation_time DESC\r\n" 
//	, nativeQuery = true)
	@Query(value = ""
			+ "SELECT R.NO as no\r\n"
			+ "       , M.NAME as name\r\n"
			+ "       , R.theme_theme as theme \r\n"
			+ "       , R.reservation_date as reservationDate \r\n"
			+ "       , CONCAT(SUBSTRING(R.reservation_time, 1, 2), ' : ', SUBSTRING(R.reservation_time, 3, 2)) as reservationTime \r\n"  
			+ "       , R.people as people\r\n"
			+ "       , R.spot_name_spot_name as spot\r\n"
			+ "  FROM reservation R\r\n"
			+ "       , `member` m \r\n"
			+ " WHERE 1 = 1 \r\n"
			+ "   AND R.member_id = M.id\r\n"
			+ " ORDER BY R.NO DESC\r\n"
			, nativeQuery = true)
	List<Map<String, String>> getReservationInventoryList();

	/*
	 * // 회원 id 랜덤 적용 이후 삭제 해야됨
	 * 
	 * @Query(value = "SELECT * FROM member ORDER BY RAND() LIMIT 1", nativeQuery =
	 * true) Member findRandomMember();
	 */
	
	
	@Query(value = ""
	+ "SELECT spot_name\r\n"
	+ "  FROM bootex.spot"
	, nativeQuery = true)
	List<Map<String, String>> getSpotList();
	
	
	// 수정 후
	// 더미 데이터 중복 벙자용 부분 추가
	boolean existsByMemberAndThemeAndSpotName(Member member, Theme theme, Spot spot);
	
	
	
	
	
	
	
	
	 @Query(value = ""
	            + "SELECT R.NO as no\r\n"
	            + "       , M.NAME as name\r\n"
	            + "       , R.theme_theme as theme \r\n"
	            + "       , R.reservation_date as reservationDate \r\n"
	            + "       , CONCAT(SUBSTRING(R.reservation_time, 1, 2), ' : ', SUBSTRING(R.reservation_time, 3, 2)) as reservationTime \r\n"  
	            + "       , R.people as people\r\n"
	            + "       , R.spot_name_spot_name as spot\r\n"
	            + "  FROM reservation R\r\n"
	            + "       , `member` M \r\n"
	            + " WHERE 1 = 1 \r\n"
	            + "   AND R.member_id = M.id\r\n"
	            + "   AND R.spot_name_spot_name = :spot \r\n"  // 지점별 필터링 추가
	            + " ORDER BY R.NO DESC\r\n"
	            , nativeQuery = true)
	    List<Map<String, String>> findReservationsBySpot(@Param("spot") String spot);

	// 수정 끝 
	
	
	
	
	
	@Query(value = ""
	+ "SELECT theme, genre, `level`, limited_time, lock_ratio, max_people, min_people, plant_ratio, sysnopsis, spot_spot_name, img_path as imgPath\r\n"
	+ "  FROM bootex.theme\r\n"
	+ " WHERE 1 = 1 \r\n"
	+ "   AND spot_spot_name = :spot"
			
	, nativeQuery = true)
	List<Map<String, String>> getThemeList(@Param("spot") String spot);

	/**
	 * @param theme
	 */
	@Query(value = ""
	+ "SELECT reservation_time as reservationTime\r\n"
	+ "       , use_yn as useYn"
	+ "  FROM common_reservationtime\r\n"
	+ " WHERE 1 = 1 \r\n"
	+ "   AND theme = :theme"
	, nativeQuery = true)
	List<Map<String, String>> getThemeReservationTime(@Param("theme") String theme);

	/**
	 * @param spot
	 * @param theme
	 * @param reservationTime
	 * @param reservationTime 
	 */
	@Query(value = ""
	+ "SELECT count(1)\r\n"
	+ "  FROM bootex.reservation\r\n"
	+ " WHERE 1 = 1 \r\n"
	+ "   AND spot_name_spot_name = :spot\r\n"
	+ "   AND theme_theme = :theme\r\n"
	+ "   AND reservation_date  = :reservationDate\r\n"
	+ "   AND reservation_time  = :reservationTime"
	, nativeQuery = true)
	int getReservationAvailable(@Param("spot") 			String spot
								,@Param("theme") 			String theme
								,@Param("reservationDate") 	String reservationDate
								,@Param("reservationTime") 	String reservationTime);
		
	
	
	
	/**
	 * @name: 서연
	 * @day : 10.23
	 * @param : 회원아이로 예약조회
	 */
	List<Reservation> findByMember(Member member);

}