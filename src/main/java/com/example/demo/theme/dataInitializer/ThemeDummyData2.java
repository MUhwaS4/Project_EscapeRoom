package com.example.demo.theme.dataInitializer;


import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import com.example.demo.spot.entity.Spot;
import com.example.demo.theme.entity.Theme;
import com.example.demo.theme.repository.ThemeRepository;

@Component
@Order(4)
public class ThemeDummyData2 implements ApplicationRunner {
	
	private final ThemeRepository themeRepository;

    public ThemeDummyData2(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Spot spot = new Spot();
		 spot.setSpotName("인천구월");

		Theme theme1 = Theme.builder()
								.theme("락인블랙 13번째 시간을 털어라")
								.spot(spot)
								.genre("추리/미션")
								.level(4)
								.limitedTime(75)
								.minPeople(2)
								.maxPeople(6)
								.lockRatio(6)
								.plantRatio(4)
								.sysnopsis("진짜 귀한 보물이 뭐냐고?<br>"
										+ "블루하트? 컬리넌? 이니그마?<br>"
										+ "진짜 귀한보물은 사람들이 그 존재자체도 모른다는 거야.<br>"
										+ "대부분 이전 문명의 유물, 그들이 신의 섭리에 거슬러<br>"
										+ "문명자체를 멸망하게 만든 초자연적인 보물이 있다는거지.<br>"
										+ "우린 마침내 사하라 한가운데에서 Bastian Black의 위치를 찾았네.<br>"
										+ "거긴 하루 한번의 시간을 돌릴수 있는 보물 <br>"
										+ "13번째 시간을 모셔놓은 곳이지.<br>"
										+ "얘기가 좀 길었군 <br>"
										+ "그곳의 보안장치를 해제해 줄 암호 해독 전문가가 필요하네.<br>"
										+ "자네 말고는 적임자를 찾을수 없어.<br>"
										+ "이렇게 부탁하겠네. 내 사례는 섭섭치 않게 챙겨주지.<br>")
								.imgPath("theme-005.jpg")
								.build();

		Theme theme2 = Theme.builder()
								.theme("시간관리국")
								.spot(spot)
								.genre("어드벤쳐")
								.level(4)
								.limitedTime(75)
								.minPeople(2)
								.maxPeople(6)
								.lockRatio(6)
								.plantRatio(4)
								.sysnopsis("2200년 부족한 인구 대비 노년층이 압도적으로 만들어진다. 노년층은 일자리가 늘어가고, 세상은 노인 위주 시대가 되어버린다.<br>"
										+ "시간 관리국 소속 직원인 승주는 어느 날,<br><br> 영생의 약을 구할 수 있는 경로에 대한 소문을 듣게 되는데… ")
								.imgPath("theme-006.jpg")
								.build();

		
		Theme theme3 = Theme.builder()
								.theme("[프리미엄]시간의 회랑")
								.spot(spot)
								.genre("드라마")
								.level(4)
								.limitedTime(60)
								.minPeople(2)
								.maxPeople(7)
								.lockRatio(3)
								.plantRatio(7)
								.sysnopsis("여기는 시간의 회랑이네<br>"
										+ "좋은 추억도, 끔찍한 사고도, 암담한 내일도<br>"
										+ "그냥 모두 흘러가고 있는 것을…”")
								.imgPath("theme-007.jpg")
								.build();
		
		
		Theme theme4 = Theme.builder()
								.theme("[프리미엄]방탈출오락실")
								.spot(spot)
								.genre("코믹")
								.level(4)
								.limitedTime(60)
								.minPeople(2)
								.maxPeople(7)
								.lockRatio(3)
								.plantRatio(7)
								.sysnopsis("[이봐 애송이~ㅋ ㅗ(^_^)ㅗ<br>"
										+ "나는 전설의 게임왕 김계미다.<br>"
										+ "어디 한번 나에게 도전해봐.<br>"
										+ "어차피 탈락할께 뻔하지만 말이야.]<br>"
										+ "- 김계미(31세. 무직) / 전설의 게임왕<br>")
								.imgPath("theme-008.jpg")
								.build();
		
		themeRepository.save(theme1);
		themeRepository.save(theme2);
		themeRepository.save(theme3);
		themeRepository.save(theme4);
		
	}

}
