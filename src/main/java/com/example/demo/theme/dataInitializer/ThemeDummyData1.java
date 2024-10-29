package com.example.demo.theme.dataInitializer;


import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import com.example.demo.spot.entity.Spot;
import com.example.demo.theme.entity.Theme;
import com.example.demo.theme.repository.ThemeRepository;


@Component
@Order(3)
public class ThemeDummyData1 implements ApplicationRunner {
	
	private final ThemeRepository themeRepository;

    public ThemeDummyData1(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Spot spot = new Spot();
		 spot.setSpotName("홍대");
		

		Theme theme1 = Theme.builder()
								.theme("[프리미엄]스탕달 신드롬")
								.spot(spot)
								.genre("공포/스릴러")
								.level(4)
								.limitedTime(75)
								.minPeople(2)
								.maxPeople(6)
								.lockRatio(6)
								.plantRatio(4)
								.sysnopsis("세간의 주목을 받던 사실주의 화가 예피모비치가 실종된지 3년.<br>"
										+ "이미 죽은게 아니냐는 소문과 함께 그의 저택엔 인기척이 전혀 없다더군<br>"
										+ "<br>"
										+ "군침이 돌지 않나.<br>"
										+ "그의 유작이라도 발견하게 되면 아주 크게 팔아 넘길 수 있을거야.")
								.imgPath("theme-001.jpg")
								.build();

		Theme theme2 = Theme.builder()
								.theme("[프리미엄]디스트로이드")
								.spot(spot)
								.genre("공포/스릴러")
								.level(3)
								.limitedTime(60)
								.minPeople(2)
								.maxPeople(6)
								.lockRatio(5)
								.plantRatio(5)
								.sysnopsis("AI로봇수칙<br>"
										+ "<br>"
										+ "1.로봇은 인간을 죽일 수 없다.<br>"
										+ "2.로봇은 감정을 가질수 없고 꿈을 꿀수 없다.<br>"
										+ "3.로봇은 절대 거짓말을 할 수 없다.")
								.imgPath("theme-002.jpg")
								.build();

		Theme theme3 = Theme.builder()
								.theme("[프리미엄 테마]미즈몰리아와 수수께끼의 책")
								.spot(spot)
								.genre("추리/미션")
								.level(2)
								.limitedTime(60)
								.minPeople(2)
								.maxPeople(7)
								.lockRatio(3)
								.plantRatio(7)
								.sysnopsis("무슨 책을 찾고있니 꼬마야.<br><br>유쾌한책 유익한책.<br> 감동적이거나 슬픈책.<br><br> 그게 아니면 ...수수께끼의 책을 찾고있니?")
								.imgPath("theme-003.jpg")
								.build();
		
		Theme theme4 = Theme.builder()
							.theme("[프리미엄]귀로여관")
							.spot(spot)
							.genre("공포/스릴러")
							.level(2)
							.limitedTime(70)
							.minPeople(2)
							.maxPeople(7)
							.lockRatio(6)
							.plantRatio(4)
							.sysnopsis("누군가를 죽인 경험이 있습니까?<br> 그 경험을 나누고 싶습니다.<br><br> 귀로 여관에서 기다리겠습니다. ")
							.imgPath("theme-004.jpg")
							.build();
					
		themeRepository.save(theme1);
		themeRepository.save(theme2);
		themeRepository.save(theme3);
		themeRepository.save(theme4);
		
	}

}
