package com.pibigstar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pibigstar.domain.RentHouse;
import com.pibigstar.domain.Variation;
import com.pibigstar.repository.RentHouseRepository;
import com.pibigstar.sort.SortResult;
import com.pibigstar.sort.SortResult1;
import com.pibigstar.sort.SortResult2;
import com.pibigstar.sort.SortResult3;
import com.pibigstar.sort.SortResult4;
import com.pibigstar.sort.SortResult5;
import com.pibigstar.sort.SortResult6;
import com.pibigstar.sort.SortResult7;
import com.pibigstar.utils.JsonResult;

@RestController
public class RentHouseController {

	@Autowired
	private RentHouseRepository repository;
	
	@RequestMapping("/listRent")
	public JsonResult getAllRentHouse(Variation variation) {
		System.out.println("Variation:"+variation);
		List<RentHouse> rentHouses = repository.findAll();
		switch (variation.getChoose()) {
		case 1:
			rentHouses = SortResult1.sort(rentHouses, variation);
			break;
		case 2:
			rentHouses = SortResult2.sort(rentHouses, variation);
			break;
		case 3:
			rentHouses = SortResult3.sort(rentHouses, variation);
			break;
		case 4:
			rentHouses = SortResult4.sort(rentHouses, variation);
			break;
		case 5:
			rentHouses = SortResult5.sort(rentHouses, variation);
			break;
		case 6:
			rentHouses = SortResult6.sort(rentHouses, variation);
			break;
		default:
			rentHouses = SortResult6.sort(rentHouses, variation);
		}

		return JsonResult.success(rentHouses, "OK!");
	}
}
