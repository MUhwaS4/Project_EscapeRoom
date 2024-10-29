package com.example.demo.spot.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.spot.dto.SpotDTO;
import com.example.demo.spot.entity.Spot;
import com.example.demo.spot.repository.SpotRepository;

@Service
public class SpotServiceImpl implements SpotService {
	@Autowired
	SpotRepository repository;
	@Override
	public List<SpotDTO> getSpotList() {
		   List<Spot> spotList = repository.findAll();
	        return spotList.stream()
	                       .map(entity->entityToDto(entity))
	                       .collect(Collectors.toList());
	    }
}

