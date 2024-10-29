package com.example.demo.theme.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.reservation.entity.CommonReservationTime;
import com.example.demo.reservation.repository.CommonReservationTimeRepository;
import com.example.demo.spot.entity.Spot;
import com.example.demo.theme.dto.ThemeDTO;
import com.example.demo.theme.entity.Theme;
import com.example.demo.theme.repository.ThemeRepository;
import com.example.demo.theme.util.FileUtil;


@Service
public class ThemeServiceImpl implements ThemeService {
   @Autowired
   ThemeRepository repository;
   
   @Autowired
   private FileUtil fileUtil;
   
   @Autowired
   CommonReservationTimeRepository commonReservationTimeRepository;
   
   // DTO를 엔티티로 변환하는 메서드
   @Override
   public Theme dtoToEntity(ThemeDTO dto) {
        Spot spot = Spot.builder()
                  .spotName(dto.getSpotName()) // DTO에서 spotName 문자열 가져오기
                  .build();

        Theme entity = Theme.builder()
                .theme(dto.getTheme())
                .spot(spot) // Spot 객체 설정
                .genre(dto.getGenre())
                .level(dto.getLevel())
                .limitedTime(dto.getLimitedTime())
                .minPeople(dto.getMinPeople())
                .maxPeople(dto.getMaxPeople())
                .lockRatio(dto.getLockRatio())
                .plantRatio(dto.getPlantRatio())
                .sysnopsis(dto.getSysnopsis())
                .imgPath(dto.getImgPath())
                .build();
      
        return entity;
   }

   // 엔티티를 DTO로 변환하는 메서드
   @Override
   public ThemeDTO entityToDto(Theme entity) {
      // Theme 엔티티를 ThemeDTO로 변환
       ThemeDTO dto = ThemeDTO.builder()
               .theme(entity.getTheme())
               .spotName(entity.getSpot().getSpotName()) // Spot의 spotName 가져오기
               .genre(entity.getGenre())
               .level(entity.getLevel())
               .limitedTime(entity.getLimitedTime())
               .minPeople(entity.getMinPeople())
               .maxPeople(entity.getMaxPeople())
               .lockRatio(entity.getLockRatio())
               .plantRatio(entity.getPlantRatio())
               .sysnopsis(entity.getSysnopsis())
               .imgPath(entity.getImgPath())
               .build();
    
   // 수정 후
   // 예약 가능한 시간 목록을 설정
    List<CommonReservationTime> reservationTimes = commonReservationTimeRepository.findByTheme(entity);
    if (!reservationTimes.isEmpty()) {
        String times = reservationTimes.stream()
                .map(CommonReservationTime::getReservationTime)
                .collect(Collectors.joining(", "));
        dto.setReservationTimes(times);
    }

    return dto;
   }
   
   //지점을 기준으로 목록을 생성
   @Override
   public List<ThemeDTO> getListBySpot(String spotName) {
      List<Theme> themes = repository.findBySpot_SpotName(spotName);
        return themes.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
   }

	//테마 등록 메소드
	@Override
	public String themeRegister(ThemeDTO dto) {		
		 //파라미터로 전달받은 dto를 엔티티로 변환
		Theme entity = dtoToEntity(dto);

		// 유틸클래스를 이용해서 파일을 폴더에 저장하고 파일이름을 반환받는다
		String imgPath = fileUtil.fileUpload(dto.getUploadFile());
		
	    if (imgPath == null || imgPath.isEmpty()) {
	        throw new RuntimeException("이미지 파일 업로드에 실패했습니다.");
	    }


      // 그리고 엔티티에 파일이름을 저장한다
       entity.setImgPath(imgPath);
      
      repository.save(entity);
      
      // Theme 엔티티를 먼저 저장
      Theme savedEntity = repository.save(entity); 
      
      // DTO로부터 예약 시간 받아서 저장
      String reservationTimes = dto.getReservationTimes();
      if (reservationTimes != null && !reservationTimes.trim().isEmpty()) {
          // 기존의 예약 시간을 삭제
          List<CommonReservationTime> existingReservations = commonReservationTimeRepository.findByTheme(entity);
          if (!existingReservations.isEmpty()) {
              commonReservationTimeRepository.deleteAll(existingReservations);
          }

          // ,를 구분자로 분리하여 예약 시간을 리스트로 변환
          List<String> timeList = Arrays.asList(reservationTimes.split("\\s*,\\s*")); // 공백도 제거
          for (String time : timeList) {
              // 각각의 시간으로 CommonReservationTime 엔티티 생성 및 저장
              CommonReservationTime reservation = CommonReservationTime.builder()
                  .reservationTime(time)
                  .theme(entity) // Theme과 연결
                  .useYn("Y") // 기본값으로 사용 가능하게 설정
                  .build();

              commonReservationTimeRepository.save(reservation); // 예약 시간 저장
          }
      }
      
      return savedEntity.getTheme(); //새로운 테마명 반환
   }


	//테마 상세조회 메소드
	@Override
	public ThemeDTO read(String theme) {
		Optional<Theme> result = repository.findById(theme);
		if(result.isPresent()) {
			Theme entity = result.get();
			ThemeDTO themeDto = entityToDto(entity); //엔티티를 DTO로 변환
			return themeDto;
		}else {
			return null;
		}
	}
	
	//테마 전체목록 조회 메소드
	@Override
	public List<ThemeDTO> allRead() {
		List<Theme> result = repository.findAll();
		List<ThemeDTO> dtoList = new ArrayList<>();
	    
	    for (Theme theme : result) {
	        ThemeDTO dto = entityToDto(theme); // 엔티티를 DTO로 변환
	        dtoList.add(dto);
	    }
	    
	    return dtoList; // 변환된 DTO 리스트 반환
		
	} 

   //테마 수정 메소드
   @Override
   public void modify(ThemeDTO dto) {

      // 전달받은 DTO에서 테마이름 꺼내고, 다시 저장
      Optional<Theme> result = repository.findById(dto.getTheme());
      if (result.isPresent()) { // 해당 게시물이 존재하는지 확인
         Theme entity = result.get();
         
         Spot spot = Spot.builder()
                     .spotName(dto.getSpotName())
                     .build();
         
           entity.setTheme(dto.getTheme());
           // 새로 첨부된 파일이 있으면 이미지 경로 업데이트
           if (dto.getImgPath() != null && !dto.getImgPath().isEmpty()) {
               entity.setImgPath(dto.getImgPath());
           }

         entity.setSpot(spot);
         entity.setGenre(dto.getGenre());
         entity.setLevel(dto.getLevel());
         entity.setLimitedTime(dto.getLimitedTime());
         entity.setMinPeople(dto.getMinPeople());
         entity.setMaxPeople(dto.getMaxPeople());
         entity.setLockRatio(dto.getLockRatio());
         entity.setPlantRatio(dto.getPlantRatio());
         entity.setSysnopsis(dto.getSysnopsis());
         
         // 다시 저장
         repository.save(entity);
         
         // 기존의 예약 시간을 삭제
         List<CommonReservationTime> existingReservations = commonReservationTimeRepository.findByTheme(entity);
         if (!existingReservations.isEmpty()) {
             commonReservationTimeRepository.deleteAll(existingReservations);
         }

         // 새로운 예약 시간 추가
         String reservationTimes = dto.getReservationTimes();
         if (reservationTimes != null && !reservationTimes.trim().isEmpty()) {
             // ,를 구분자로 분리하여 예약 시간을 리스트로 변환
             List<String> timeList = Arrays.asList(reservationTimes.split("\\s*,\\s*")); // 공백도 제거
             for (String time : timeList) {
                 // 각각의 시간으로 CommonReservationTime 엔티티 생성 및 저장
                 CommonReservationTime reservation = CommonReservationTime.builder()
                     .reservationTime(time)
                     .theme(entity) // Theme과 연결
                     .useYn("Y") // 기본값으로 사용 가능하게 설정
                     .build();

                 commonReservationTimeRepository.save(reservation); // 예약 시간 저장
             }
         }
      }

   }

   //테마 삭제 메소드
   @Override
   public void remove(String theme) {
        Optional<Theme> result = repository.findById(theme);

          // 게시물이 존재하면 삭제
          if (result.isPresent()) {
              Theme entity = result.get();

              // 연관된 CommonReservationTime 데이터 삭제
              List<CommonReservationTime> existingReservations = commonReservationTimeRepository.findByTheme(entity);
              if (!existingReservations.isEmpty()) {
                  commonReservationTimeRepository.deleteAll(existingReservations);
              }

		        // 테마 삭제
		        repository.deleteById(theme);
		    }
		}

}
