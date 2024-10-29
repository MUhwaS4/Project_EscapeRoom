package com.example.demo.spot.dataInitializer;

import java.util.Optional;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.demo.spot.entity.Spot;
import com.example.demo.spot.repository.SpotRepository;


@Component
@Order(2)
public class SpotDummyData implements ApplicationRunner {
	
	private final SpotRepository spotRepository;

    public SpotDummyData(SpotRepository spotRepository) {
        this.spotRepository = spotRepository;
    }

    
	@Override
	public void run(ApplicationArguments args) throws Exception {
        saveSpotIfNotExist("홍대", "서울특별시 마포구 338-48 , 3층", "02-1234-5678", 37.556724, 126.922349);
        saveSpotIfNotExist("인천구월", "구월동 1467-1번지 대건빌딩 501호", "02-1234-5678", 37.448828, 126.708632);
        saveSpotIfNotExist("서면", "중앙대로692번길 43 금양빌딩 5층", "02-1234-5678", 35.159546, 129.059475);
    }

    private void saveSpotIfNotExist(String spotName, String address, String tel, double latitude, double longitude) {
        Optional<Spot> existingSpot = spotRepository.findById(spotName);
        if (!existingSpot.isPresent()) {
            Spot spot = Spot.builder()
                .spotName(spotName)
                .address(address)
                .tel(tel)
                .latitude(latitude)
                .longitude(longitude)
                .build();
            spotRepository.save(spot);
        }
    }
//	public void run(ApplicationArguments args) throws Exception {
//	
//		Spot spot1 = Spot.builder()
//						.spotName("홍대")
//						.address("서울특별시 마포구 338-48 , 3층")
//						.tel("02-1234-5678")
//						.latitude(37.556724)
//						.longitude(126.922349)
//						.build();
//
//		Spot spot2 = Spot.builder()
//							.spotName("인천구월")
//							.address("구월동 1467-1번지 대건빌딩 501호")
//							.tel("02-1234-5678")
//							.latitude(37.448828)
//							.longitude(126.708632)
//							.build();
//
//		Spot spot3 = Spot.builder()
//							.spotName("서면")
//							.address("중앙대로692번길 43 금양빌딩 5층")
//							.tel("02-1234-5678")
//							.latitude(35.159546)
//							.longitude(129.059475)
//							.build();
//
//		spotRepository.save(spot1);
//		spotRepository.save(spot2);
//		spotRepository.save(spot3);			
//		
//	}

}
