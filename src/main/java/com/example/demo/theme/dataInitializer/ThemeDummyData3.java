package com.example.demo.theme.dataInitializer;


import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import com.example.demo.spot.entity.Spot;
import com.example.demo.theme.entity.Theme;
import com.example.demo.theme.repository.ThemeRepository;

@Component
@Order(5)
public class ThemeDummyData3 implements ApplicationRunner {
	
	private final ThemeRepository themeRepository;

    public ThemeDummyData3(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		 Spot spot = new Spot();
		 spot.setSpotName("서면");
		

		Theme theme1 = Theme.builder()
									.theme("[프리미엄]유품정리사")
									.spot(spot)
									.genre("공포/스릴러")
									.level(3)
									.limitedTime(70)
									.minPeople(2)
									.maxPeople(6)
									.lockRatio(5)
									.plantRatio(5)
									.sysnopsis("어이. 정리사 양반 도착했수?<br>"
											+ "아유 좀 꺼림직해도 잘 좀 부탁혀요<br>"
											+ "무슨 일 있으면 얘기하구. 끝나면 연락줘요.")
									.imgPath("theme-009.jpg")
									.build();

			Theme theme2 = Theme.builder()
										.theme("[프리미엄]트라이앵글")
										.spot(spot)
										.genre("어드벤쳐")
										.level(4)
										.limitedTime(90)
										.minPeople(2)
										.maxPeople(6)
										.lockRatio(6)
										.plantRatio(4)
										.sysnopsis("거리에서 사람들이 사라지고 있다.<br><br> 나는 보험조사관으로서 실종자들을 찾아야한다.")
										.imgPath("theme-010.jpg")
										.build();

			Theme theme3 = Theme.builder()
										.theme("[프리미엄]펜타킬")
										.spot(spot)
										.genre("공포/스릴러")
										.level(2)
										.limitedTime(70)
										.minPeople(2)
										.maxPeople(7)
										.lockRatio(3)
										.plantRatio(7)
										.sysnopsis("죽이는 게 있는데... 들어볼래요?")
										.imgPath("theme-011.jpg")
										.build();
					

			Theme theme4 = Theme.builder()
										.theme("홍연")
										.spot(spot)
										.genre("감성/판타지")
										.level(2)
										.limitedTime(70)
										.minPeople(2)
										.maxPeople(7)
										.lockRatio(3)
										.plantRatio(7)
										.sysnopsis("저잣거리에 나가보니 재밌는 소문이 돌더라.<br>"
												+ "마을 어귀에 실을 파는 노인이 있는데, <br>"
												+ "그곳에 파는  홍실을 사서 두사람의 손가락을 묶으면<br>"
												+ "부부의 연을 맺을 수 있대.<br>"
												+ "<br>"
												+ "본디 모든 사람의 인연은 이 운명의 실로 묶여있는 것을<br>"
												+ "그래. 이번에는 어떤 색 실을 택할테냐?")
										.imgPath("theme-012.jpg")
										.build();
		themeRepository.save(theme1);
		themeRepository.save(theme2);
		themeRepository.save(theme3);
		themeRepository.save(theme4);
		
	}

}
