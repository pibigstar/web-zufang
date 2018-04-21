package com.pibigstar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pibigstar.domain.RentHouse;
import com.pibigstar.domain.Variation;
import com.pibigstar.repository.RentHouseRepository;
import com.pibigstar.util.JsonResult;
import com.pibigstar.util.SortResult;

@RestController
public class RentHouseController {

	@Autowired
	private RentHouseRepository repository;
	
	@RequestMapping("/listRent")
	public JsonResult getAllRentHouse(Variation variation) {
		
		List<RentHouse> rentHouses = repository.findAll();
		rentHouses = SortResult.sort(rentHouses, variation);
		return JsonResult.success(rentHouses, "OK!");
	}
	
	
	
	
	
}
