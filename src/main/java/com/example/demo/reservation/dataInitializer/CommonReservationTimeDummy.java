package com.example.demo.reservation.dataInitializer;


import java.util.Optional;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.demo.reservation.entity.CommonReservationTime;
import com.example.demo.reservation.repository.CommonReservationTimeRepository;
import com.example.demo.theme.entity.Theme;


@Component
@Order(8)
public class CommonReservationTimeDummy implements ApplicationRunner {
	
	private final CommonReservationTimeRepository commonReservationTimeRepository;

    public CommonReservationTimeDummy(CommonReservationTimeRepository commonReservationTimeRepository) {
        this.commonReservationTimeRepository = commonReservationTimeRepository;
    }
    
    public void run(ApplicationArguments args) throws Exception {
       	

        // 테마 배열 생성 및 고유 테마명 설정
        Theme[] themes = new Theme[12];
        String[] themeNames = {
            "[프리미엄]스탕달 신드롬",
            "[프리미엄]디스트로이드",
            "[프리미엄 테마]미즈몰리아와 수수께끼의 책",
            "[프리미엄]귀로여관",
            "락인블랙 13번째 시간을 털어라",
            "시간관리국",
            "[프리미엄]시간의 회랑",
            "[프리미엄]방탈출오락실",
            "[프리미엄]유품정리사",
            "[프리미엄]트라이앵글",
            "[프리미엄]펜타킬",
            "홍연"
        };

        // 테마 객체 생성
        for (int i = 0; i < themes.length; i++) {
            themes[i] = new Theme();
            themes[i].setTheme(themeNames[i]); // 각 테마 이름 설정
        }

        // 예약 시간 배열 설정
        String[][] reservationTimes = {
            {"1000", "1200", "1400", "1600", "1800", "2000", "2200", "2400"}, // 테마1
            {"1020", "1220", "1420", "1620", "1820", "2020", "2220", "2420"}, // 테마2
            {"1030", "1230", "1430", "1630", "1830", "2030", "2230", "2430"}, // 테마3
            {"1040", "1240", "1440", "1640", "1840", "2040", "2240", "2440"}, // 테마4
            // 여기에 각 테마에 대한 예약 시간을 추가해주시면 됩니다.
        };

        // 더미 데이터 생성
        for (int i = 0; i < themes.length; i++) {
            // 각 테마에 대해 단일 예약 시간 배열을 사용
            String[] timesForCurrentTheme = reservationTimes[i % reservationTimes.length]; // 12개 테마에 대해 4개의 배열 반복

            for (String time : timesForCurrentTheme) {
                // 기존 데이터 확인
                Optional<CommonReservationTime> existingTime = commonReservationTimeRepository.findByReservationTimeAndTheme(time, themes[i]);
                
                if (!existingTime.isPresent()) { // 중복 데이터가 존재하지 않을 경우
                    CommonReservationTime reservationTime = CommonReservationTime.builder()
                            .reservationTime(time)
                            .useYn("Y") // 사용 가능 여부
                            .theme(themes[i]) // 테마 설정
                            .build();

                    commonReservationTimeRepository.save(reservationTime); // 데이터베이스에 저장
                } else {
                    System.out.println("중복 데이터 발견: " + time + " - " + themes[i].getTheme());
                }
            }
        }
    }
}