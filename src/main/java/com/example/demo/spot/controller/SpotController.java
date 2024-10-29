package com.example.demo.spot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.spot.dto.SpotDTO;
import com.example.demo.spot.service.SpotService;

@Controller
@RequestMapping("/spot")
public class SpotController {
	@Autowired
	SpotService service;
	
	@GetMapping("/spot")
	public String spot(Model model) {
		List<SpotDTO> spotList = service.getSpotList();
        model.addAttribute("spots", spotList);
        return "/spot/spot"; 
	}

}
