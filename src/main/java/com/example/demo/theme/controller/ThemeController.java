package com.example.demo.theme.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.theme.dto.ThemeDTO;
import com.example.demo.theme.repository.ThemeRepository;
import com.example.demo.theme.service.ThemeService;
import com.example.demo.theme.util.FileUtil;



@Controller
@RequestMapping("/theme")
public class ThemeController {
	
	//테마 목록화면
	@Autowired
	ThemeService service;
	
	@Autowired
	FileUtil fileUtil;
	
    @Autowired
    private ThemeRepository repository;

	@GetMapping("/theme-list")
	public String themeList(@RequestParam(name = "spot", defaultValue = "홍대") String spotName, Model model) {
		List<ThemeDTO> list = service.getListBySpot(spotName);
		model.addAttribute("list",list);
		return "theme/theme-list";
	}
	
	// 추가 이미지 REST API 엔드포인트 추가
    @GetMapping("/themeListApi")
    @ResponseBody
    public List<Map<String, Object>> getThemeList(@RequestParam String spot) {
        List<ThemeDTO> themes = service.getListBySpot(spot);
        List<Map<String, Object>> responseList = new ArrayList<>();

        for (ThemeDTO theme : themes) {
            Map<String, Object> themeMap = new HashMap<>();
            themeMap.put("theme", theme.getTheme());
            themeMap.put("imgPath", theme.getImgPath());
            themeMap.put("imageUrl", "/uploadfile/" + theme.getImgPath()); // 직접 생성한 URL 추가
            themeMap.put("genre", theme.getGenre());
            themeMap.put("level", theme.getLevel());
            themeMap.put("limitedTime", theme.getLimitedTime());
            themeMap.put("minPeople", theme.getMinPeople());
            themeMap.put("maxPeople", theme.getMaxPeople());
            themeMap.put("lockRatio", theme.getLockRatio());
            themeMap.put("plantRatio", theme.getPlantRatio());
            themeMap.put("sysnopsis", theme.getSysnopsis());
            responseList.add(themeMap);
        }

        return responseList;
    }
	
	//테마 등록화면
	@GetMapping("/theme-register")
	public String themeRegister() {
		return "theme/theme-register";
	}
	
	//테마 등록처리
	@PostMapping("/theme-register")
	public String registerPost(ThemeDTO dto, RedirectAttributes redirectAttributes) {
		// 테마를 등록하고 새로운 테마 이름 반환
		String theme = service.themeRegister(dto);
		
		// 목록화면에 새로운 테마전달
		redirectAttributes.addFlashAttribute("msg", theme);
		
		// 목록화면으로 이동.
		return "redirect:/theme/theme-list";
	}
	
	//테마 수정화면
	@GetMapping("/theme-modify")
	public void modify(@RequestParam(name = "theme") String theme, Model model) {
		 ThemeDTO dto = service.read(theme); // 테마명 조회
	     model.addAttribute("dto", dto); // 화면에 게시물 정보 전달
	}
	
	//중복 테마명 확인화면
	@GetMapping("/check")
	@ResponseBody
	public boolean themeCheck(@RequestParam(name = "theme") String theme) {
		ThemeDTO dto = service.read(theme);
		return dto !=null;
	}
	
	//테마 수정처리
	 @PostMapping("/theme-modify")
	    public String modifyPost(ThemeDTO dto,
	    		@RequestParam("uploadFile") MultipartFile uploadFile,
	    		RedirectAttributes redirectAttributes) {
		 
			// 업로드된 파일이 있는 경우 파일을 저장하고 이미지 경로 설정
		    if (!uploadFile.isEmpty()) {
		        String imgPath = fileUtil.fileUpload(uploadFile); // FileUtil을 이용해 파일 저장
		        dto.setImgPath(imgPath); // DTO에 이미지 경로 설정
		    }
		    
	        // 게시물 수정
	        service.modify(dto);
	        // 리다이렉트 주소에 파라미터 추가 (?no=1)
	        redirectAttributes.addAttribute("theme", dto.getTheme());
	        // 상세화면으로 이동
	        return "redirect:/theme/theme-list";

	        // addFlashAttribute(): 리다이렉트할 화면에 데이터를 보내는 함수
	        // addAttribute(): 리다이렉트 주소에 파라미터를 추가하는 함수
	    }

	
	//테마 삭제처리
	@PostMapping("/remove")
	public String remove(@RequestParam("theme") String theme) {
		//게시물을 삭제하고 목록화면으로 이동
		service.remove(theme);
		return "redirect:/theme/theme-list";
	}
	
}
