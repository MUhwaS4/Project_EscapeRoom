package com.example.demo.reservation.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.reservation.dto.ReservationDTO;
import com.example.demo.reservation.entity.Reservation;
import com.example.demo.reservation.repository.CommonReservationTimeRepository;
import com.example.demo.reservation.repository.ReservationRepository;
import com.example.demo.spot.entity.Spot;
import com.example.demo.spot.repository.SpotRepository;
import com.example.demo.theme.entity.Theme;
import com.example.demo.theme.repository.ThemeRepository;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
    ReservationRepository reservationRepository;
	
	@Autowired
	CommonReservationTimeRepository commonReservationTimeRepository;
	
	 @Autowired
	 MemberRepository memberRepository;
	
	/**
	 * @param log
	 */

	private void logPrint(Object log) {
		LocalDateTime now = LocalDateTime.now();
		System.out.print(now + " 로그 완료 ");
		System.out.println(log);
	}
	
    /**
     * @param mapList
     */
    private static List<HashMap<String, String>> convertToHashMapList(List<Map<String, String>> mapList) {
        List<HashMap<String, String>> hashMapList = new ArrayList<>();

        for (Map<String, String> map : mapList) {
            // 각 Map을 HashMap으로 변환하여 리스트에 추가
            hashMapList.add(new HashMap<>(map));
        }

        return hashMapList;
    }
    
    /**
     * @param hashMapList
     */
    public static List<HashMap<String, String>> convertToStringMapList(List<HashMap<String, String>> hashMapList) {
        List<HashMap<String, String>> stringMapList = new ArrayList<>();
        for (HashMap<String, String> hashMap : hashMapList) {
            HashMap<String, String> stringMap = new HashMap<>();
            for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                // Object 타입을 String으로 변환
                stringMap.put(entry.getKey(), String.valueOf(entry.getValue()));
            }
            stringMapList.add(stringMap);
        }
        return stringMapList;
    }    
	
	
	@Override
	// 예약생성
	public ReservationDTO createReservation(ReservationDTO reservationDTO) { 
		
		Reservation reservation = dtoToEntity(reservationDTO);
       
		Reservation savedReservation = reservationRepository.save(reservation);
        
		return convertToDTO(savedReservation);

		
	}

	@Override
	// 예약 조회 (예약 번호로 조회)
	public ReservationDTO getReservationById(int no) {  
		 
		Reservation reservation = reservationRepository.findById(no).orElseThrow(() -> new IllegalArgumentException("해당 예약을 찾을 수 없습니다."));
	       
		return convertToDTO(reservation);
			
			}
	

	@Override
	// 모든 예약 조회
	public List<ReservationDTO> getAllReservations() { 
		
		List<Reservation> reservations = reservationRepository.findAll();
	       
		return reservations.stream().map(this::convertToDTO).collect(Collectors.toList());
	    }

	@Override
	// 예약 수정
	public ReservationDTO updateReservation(int no, ReservationDTO reservationDTO) { 
		
		Reservation existingReservation = reservationRepository.findById(no)
	                .orElseThrow(() -> new IllegalArgumentException("해당 예약을 찾을 수 없습니다."));
	        
	        // 예약 정보를 업데이트
	        existingReservation.setPeople(reservationDTO.getPeople());
	        existingReservation.setAmount(reservationDTO.getAmount());
	        
	        Reservation updatedReservation = reservationRepository.save(existingReservation);
	        return convertToDTO(updatedReservation);
	}

	// 예약 삭제 
	@Override
	public void deleteReservation(int no) { 
	    if (reservationRepository.existsById(no)) {
	        reservationRepository.deleteById(no);
	        System.out.println("예약 삭제 성공: " + no);
	    } else {
	        System.err.println("삭제할 예약을 찾을 수 없습니다: " + no);
	        throw new IllegalArgumentException("삭제할 예약을 찾을 수 없습니다.");
	    }
	}


	/**
	 * name  : insertReservationInfo
	 * desc  : 예약 정보 입력
	 */
	@Override
	public void insertResrvationInfo(Map<String, String> data, String member_id) {
		/* 확인 */
		System.out.println(data);
		
		/* 쿼리 수행 전달 인자 세팅 */
		int amount					= Integer.parseInt(data.get("amount").replace("원", ""));
		int people 					= Integer.parseInt(data.get("people"));
		String reservation_date 	= data.get("reservationDate");
		String reservation_time 	=  data.get("time").replace(":", "");
		System.out.println("time확인 " + reservation_time);
		
		
		// 로그인 회원 가져오기
	    String spot_name_spot_name = data.get("spot");
	    String theme_theme = data.get("selectedTheme");

	    int result = reservationRepository.insertResrvationInfo(amount,
	                                                            people,
	                                                            reservation_date,
	                                                            reservation_time,
	                                                            member_id,
	                                                            spot_name_spot_name,
	                                                            theme_theme);
	    System.out.println(result);
	}

	@Override
	public List getReservationInventoryList(Map<String, String> data) {
		// 리턴 변수 선언
		logPrint("1. getReservationInventoryList 시작");
		
		if (data.containsKey("spot") && !data.get("spot").isEmpty()) {
	        return reservationRepository.findReservationsBySpot(data.get("spot"));
	    }
	    return reservationRepository.getReservationInventoryList();
		
	}


	/**
	 * @param data
	 */
	@Override
	public List<Map<String, String>> getSpotList(Map<String, String> data) {
		List<Map<String, String>> resultData = reservationRepository.getSpotList();
		return resultData;
	}
	

	@Override
	public List<HashMap<String, String>> getThemeList(Map<String, String> data) {
		System.out.println(data);
		// 1. 지점별 테마 목록 가져오기
		List<HashMap<String, String>> themeList = convertToStringMapList(convertToHashMapList(reservationRepository.getThemeList(data.get("spot"))));
		
		
		// 2. 테마별 예약 시간 추출하기
		for(Map<String, String> themeMap : themeList) {
			String reserVationSplitString = "";
			List<Map<String, String>> reservationTimeList = reservationRepository.getThemeReservationTime(themeMap.get("theme"));
			for(Map<String, String> reservationTimeMap : reservationTimeList) {
				String reservationTime 	= reservationTimeMap.get("reservationTime"); /* 예약 시간 */ 
				Object useYnObj			= reservationTimeMap.get("useYn"); /* 예약 가능 */
				String useYn			= useYnObj.toString(); /* 예약 가능 */
				// 3.예약이 가능하다면 현재 기 예약건이 존재하는지 확인한다.
				if(useYn != null && useYn.equals("Y")) {
					String spot = themeMap.get("spot_spot_name");
					String theme = themeMap.get("theme");
					String reservationDate = data.get("selectedDate");
					int dupCheck = reservationRepository.getReservationAvailable(spot,theme,reservationDate, reservationTime);
					if (dupCheck > 0) {
						useYn = "N";
					}
				}
				reserVationSplitString += reservationTime + "+" + useYn.toString() + "|";
			}
			
		// ** 이미지 URL 설정 추가 **
			String imgPath = themeMap.get("imgPath");
			if (imgPath != null && !imgPath.isEmpty()) {
				themeMap.put("imageUrl", "/uploadfile/" + imgPath); // 이미지 URL 설정
			}
			
			themeMap.put("reservationTime", reserVationSplitString);
		}
		
		return themeList;
	}

	@Override
	public List<Map<String, String>> getReservationInventoryBySpot(String spot) {
		return reservationRepository.findReservationsBySpot(spot);
	}


}