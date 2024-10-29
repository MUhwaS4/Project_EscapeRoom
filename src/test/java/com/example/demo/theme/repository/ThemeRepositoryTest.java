package com.example.demo.theme.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.spot.entity.Spot;
import com.example.demo.spot.repository.SpotRepository;
import com.example.demo.theme.entity.Theme;

@SpringBootTest
public class ThemeRepositoryTest {
	@Autowired
	ThemeRepository  themeRepository;
	
	@Autowired
	SpotRepository spotRepository;
	@Test void 지점등록 () {
		//지점 등록
				Spot spot1 = Spot.builder()
								.spotName("홍대")
								.address("서울특별시 마포구 338-48 , 3층")
								.tel("02-1234-5678")
								.latitude(37.556724)
								.longitude(126.922349)
								.build();
				
				Spot spot2 = Spot.builder()
								.spotName("인천구월")
								.address("구월동 1467-1번지 대건빌딩 501호")
								.tel("02-1234-5678")
								.latitude(37.448828)
								.longitude(126.708632)
								.build();
				
				Spot spot3 = Spot.builder()
								.spotName("서면")
								.address("중앙대로692번길 43 금양빌딩 5층")
								.tel("02-1234-5678")
								.latitude(35.159546)
								.longitude(129.059475)
								.build();
				
				spotRepository.save(spot1);
				spotRepository.save(spot2);
				spotRepository.save(spot3);			
	}
	
	
	@Test
	public void 홍대테마등록() {
		
		Spot spot = Spot.builder()
						.spotName("홍대")
						.build();
		
		Theme theme1 = Theme.builder()
							.theme("바티칸")
							.spot(spot)
							.genre("추리")
							.level(4)
							.limitedTime(75)
							.minPeople(2)
							.maxPeople(6)
							.lockRatio(6)
							.plantRatio(4)
							.sysnopsis("여러분은 바티칸 성당 안에서 모든 미스터리를 풀면 금은보화를 획득할 수 있다는 소문을 들어보셨나요?"
									+ "성당 속 그림과 물건들이 사실 금은보화로 가는 길이라니!"
									+ ""
									+ "경비가 느슨해지는 60분, 보물을 찾아 바티칸의 성당 깊은 곳으로 잠입해 보세요.")
							.build();
	
		Theme theme2 = Theme.builder()
							.theme("악몽")
							.spot(spot)
							.genre("공포")
							.level(3)
							.limitedTime(60)
							.minPeople(2)
							.maxPeople(6)
							.lockRatio(5)
							.plantRatio(5)
							.sysnopsis("여러분은 3년 동안 같은 악몽 속에서 괴로워하고 있습니다."
									+ "그 악몽은 예전에 살았던 아파트를 배경으로 흘러가는데요."
									+ "과연 그곳에서 무슨 일이 있었길래 악몽에 갇혀버린 걸까요?"
									+ ""
									+ "예전에 살던 아파트를 찾아가서 악몽의 원인을 찾아 해결해 보려고 합니다."
									+ ""
									+ "잠깐만.. 아파트 분위기가 왜 이래...?")
							.build();
		
		Theme theme3 = Theme.builder()
							.theme("알카트라즈")
							.spot(spot)
							.genre("어드벤쳐")
							.level(2)
							.limitedTime(60)
							.minPeople(2)
							.maxPeople(7)
							.lockRatio(3)
							.plantRatio(7)
							.sysnopsis("여러분은 누명을 쓰고 알카트라즈 감옥에 수감되었습니다."
									+ "누명이 쉽게 벗어지지 않자 탈옥을 결심하게 되는데요."
									+ ""
									+ "감옥에 탈출 도구들은 모두 잘 숨겨놓았습니다."
									+ ""
									+ "마침 오늘, 옆 수감동에 불이 나서 감옥이 소란스러운데요."
									+ "이 틈을 타서 탈출을 시도해 보세요!")
							.build();
	 
		themeRepository.save(theme1);
		themeRepository.save(theme2);
		themeRepository.save(theme3);
	}
	
	@Test
	public void 구월테마등록() {
		
		Spot spot = Spot.builder()
						.spotName("인천구월")
						.build();
		
		Theme theme1 = Theme.builder()
							.theme("인하로 77번 길")
							.spot(spot)
							.genre("추리")
							.level(4)
							.limitedTime(75)
							.minPeople(2)
							.maxPeople(6)
							.lockRatio(6)
							.plantRatio(4)
							.sysnopsis("동생과 함께 산책을 하는데 뒤에 수상한 어른들이 따라오는 것을 발견합니다."
									+ "도망치려고 했지만 꼼짝없이 기절하게 되고, 범인들의 아지트처럼 보이는 곳에서 눈을 뜨게 됩니다."
									+ "범인들이 잠시 밥을 먹으러 간 사이,"
									+ ""
									+ "지금이다! 지금 나가야해!"
									+ ""
									+ "과연 여러분은 동생과 이곳을 빠져나갈 수 있을까요?")
							.build();
	
		Theme theme2 = Theme.builder()
							.theme("신데렐라")
							.spot(spot)
							.genre("어드벤쳐")
							.level(4)
							.limitedTime(75)
							.minPeople(2)
							.maxPeople(6)
							.lockRatio(6)
							.plantRatio(4)
							.sysnopsis("오늘은 왕자님의 신부를 결정하는 파티가 열리는 날!"
									+ "하지만 계모와 언니들은 신데렐라를 방에 감금 시키고, 왕궁으로 떠나버렸다."
									+ "혼자 남은 신데렐라에게는 무도회에 타고 갈 마차도, 신을 예쁜 구두도 없다."
									+ "무도회까지 남은 시간은 단 1시간!"
									+ "시간 안에 탈출하여 왕자님의 마음을 사로잡아라!")
							.build();

		
		Theme theme3 = Theme.builder()
							.theme("장기밀매")
							.spot(spot)
							.genre("공포")
							.level(4)
							.limitedTime(60)
							.minPeople(2)
							.maxPeople(7)
							.lockRatio(3)
							.plantRatio(7)
							.sysnopsis("퇴근길, 간단한 회식을 마치고 집으로 귀가 중이던 당신 아무생각없이 걷던중," 
									+"머리 뒤쪽에 통증과 함께 눈이 감겼다." 
									+"등에 느껴지는 차가운감촉."
									+"코를 찌르는 시체 썩는 악취에 눈을 떠보니 어둡고 너무도 공포스러운 밀실 손은 수갑으로 채워져 있어 움질일수가 없다."
									+"대체 이곳은 어딜까? 당신은 장기밀매단에 납치되어 한 시간 후에는 장기적출 수술을 받고 죽는 운명에 처해있다."
									+"제한시간 단 한 시간 방안의 모든 단서를 이용하여 이 밀실을 탈출하라.")
							.build();
	 
		themeRepository.save(theme1);
		themeRepository.save(theme2);
		themeRepository.save(theme3);
	}
	
	@Test
	public void 서면테마등록() {
		
		Spot spot = Spot.builder()
						.spotName("서면")
						.build();
		
		Theme theme1 = Theme.builder()
						.theme("타이타닉")
						.spot(spot)
						.genre("어드벤쳐")
						.level(3)
						.limitedTime(60)
						.minPeople(2)
						.maxPeople(6)
						.lockRatio(5)
						.plantRatio(5)
						.sysnopsis("1912년 4월 10일. 영국의 사우스햄프턴에서 타이타닉호가 출항했다. 에드워드 존 스미스 선장과 승무원, 승객을 합쳐 2,200여명이 배에 탑승했다."
								+ "프랑스의 쉘부르와 아일랜드의 퀸스타운을 거쳐 미국의 뉴욕으로 향하다 4월 14일 23시 40분, 북대서양의 뉴펀들랜드로부터 남서쪽으로 640㎞ 떨어진 바다에서 빙산에 충돌한다."
								+ "3등석 객실에 탑승 중이던 우리 일행은 굉음과 함께 잠에서 깼다. 본능적으로 타이타닉호에서 탈출해야 함을 직감한다. 객실의 문은 복도 쪽에 차기 시작한 물의 수압에 의해 열리지 않는다. 객실 상부로 어떻게든 올라가 밖으로 나갈 수 있는 길을 찾아야만 한다. "
								+ "완전 침몰까지 우리에게 주어진 시간은 단 한 시간….."
								+ "우리는 이대로 타이타닉호와 영원히 차디찬 얼음 바다 속으로 사라질 것인가!")
						.build();
		
		Theme theme2 = Theme.builder()
							.theme("타워")
							.spot(spot)
							.genre("추리")
							.level(4)
							.limitedTime(75)
							.minPeople(2)
							.maxPeople(6)
							.lockRatio(6)
							.plantRatio(4)
							.sysnopsis("‘대한민국 최고의 고층 빌딩, CUBE Tower’ "
									+ "멋진 추억을 만들기 위해 100층 스카이 라운지에서 열리는 파티에 참석한 당신! "
									+ "가장 화려하고 도시에서 가장 빛나던 100층에서 화재가 발생하게된다. 인생 최고의 순간이 최악의 순간으로 치닫고있다!! "
									+ "유일한 탈출구였던 엘리베이터는 화재로 인해 작동을 멈췄고, "
									+ "지금 이순간 당신의 눈에 들어온것은 이미 오래전 작동을 멈춘듯해 보이는 화물용 엘리베이터! "
									+ "우여곡절 끝에 탑승한 엘리베이터는 문이 닫히자 작동을 멈췄다. 당신의 인생이 타워와 함께 불타버리기전 주어진 시간은 단 60분! "
									+ "스산한 느낌이 감도는 이 엘리베이터 안에서 작동암호를 풀고 무사히 탈출해야한다. ")
							.build();
		
		Theme theme3 = Theme.builder()
						.theme("집착")
						.spot(spot)
						.genre("추리")
						.level(2)
						.limitedTime(60)
						.minPeople(2)
						.maxPeople(7)
						.lockRatio(3)
						.plantRatio(7)
						.sysnopsis("후문에서 의문의 남자와 함께 있는 모습이 찍힌 CCTV 영상. "
								+ "실종 후, 몸값을 요구하는 연락도 없었고, 핸드폰 위치 추적도 소용없었다. "
								+ "할 수 있는 일은 혼자 미친듯이 돌아다니며 실종 전단지를 뿌리는 일 뿐이었다."
								+ "다른 날과 마찬가지로 인근 지하철역에서 전단지를 나눠주고 있는 도중, 너무나 익숙한 향기에 고개를 돌렸다.")
						.build();
				themeRepository.save(theme1);
				themeRepository.save(theme2);
				themeRepository.save(theme3);
			}
}
