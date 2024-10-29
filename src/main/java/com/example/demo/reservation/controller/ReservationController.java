package com.example.demo.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.reservation.dto.ReservationDTO;
import com.example.demo.reservation.service.ReservationService;
import com.example.demo.theme.dto.ThemeDTO;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/reservation")
public class ReservationController {
    
	  	@Autowired
	    private ReservationService reservationService;

	    // 테마 예약 목록 조회
	    @GetMapping("/list")
	    public String getReservations() {
	        return "/reservation/list";
	        
	    }
	    
	    
	    // 정보 입력 페이지로 이동 (추가된 GET 매핑)
	    @GetMapping("/information")
	    public String showReservationInformation(@RequestParam("theme") String theme, 
									    		@RequestParam("date") String date,	
								                @RequestParam("spot") String spot,
								                @RequestParam("time") String time,
								                Model model) {
	    	model.addAttribute("selectedTheme", theme);
	        model.addAttribute("reservationDate", date);
	        model.addAttribute("spot", spot);
	        model.addAttribute("selectedTime", time);
	        
	        return "reservation/information"; // 정보 입력 페이지 반환
	        
	    }
	    
	    // 예약 등록
	    @PostMapping("/information")
	    public void createReservationInformation(@RequestBody Map<String, String> data, Principal principal) {
	    	String id = principal.getName();
	    	reservationService.insertResrvationInfo(data, id);
	    }


	    /**
	     * @param model
	     */
	    @GetMapping("/inventory")
	    public String showReservationInventory(Model model) {
	        return "reservation/inventory"; // inventory.html 반환
	    }
	    
	    /**
	     * @description : @ResponseBody를 붙여 rset API 로 활용 하도록 변경
	     * @param model
	     */
	    @GetMapping("/inventoryList")
	    @ResponseBody
	    public List<Map<String, String>> getReservationInventoryList(@RequestParam Map<String, String> data, Model model) {
	        if (data.containsKey("spot") && !data.get("spot").isEmpty()) {
	            // 지점 정보가 있으면 해당 지점의 예약 목록만 필터링하여 반환
	            return reservationService.getReservationInventoryBySpot(data.get("spot"));
	        } else {
	            // 지점 정보가 없으면 전체 예약 목록 반환
	            return reservationService.getReservationInventoryList(new HashMap<>());
	        }
	    }
	    
	    
	    /**
	     * @param data
	     */
	    @GetMapping("/spotList")
	    @ResponseBody
	    public List<Map<String, String>> getSpotList(@RequestParam Map<String, String> data) {
	    	// 모든 테마 정보와 기 예약 정보 가져오기
	    	List<Map<String, String>> reservationsList = reservationService.getSpotList(data); 
	    	
	        return reservationsList; // inventory.html 반환
	    }

	    /**
	     * @param data
	     */
	    @GetMapping("/themeList")
	    @ResponseBody
	    public List<HashMap<String, String>> getTehemeList(@RequestParam Map<String, String> data) {
	        // 모든 테마 정보와 기 예약 정보 가져오기
	        List<HashMap<String, String>> reservationsList = reservationService.getThemeList(data);

	        // 이미지 URL 생성 추가
	        for (HashMap<String, String> theme : reservationsList) {
	            String imgPath = theme.get("imgPath");
	            if (imgPath != null && !imgPath.isEmpty()) {
	                String imageUrl = "/uploadfile/" + imgPath; // 이미지 URL 생성
	                theme.put("imageUrl", imageUrl);
	            }
	        }
	        return reservationsList; // inventory.html 반환
	    }

	    
	    // 예약 목록 삭제 추가 
	    @DeleteMapping("/delete/{no}")
	    public ResponseEntity<Void> deleteReservation(@PathVariable("no") int no) {
	        try {
	            reservationService.deleteReservation(no);
	            return ResponseEntity.ok().build();
	        } catch (IllegalArgumentException e) {
	            // 예약이 존재하지 않을 경우 404 반환
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        } catch (Exception e) {
	            // 그 외의 예외는 500 에러
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }
}


	