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
		//修改此处SortResult5 为SortResult4 则使用排序4算法
		rentHouses = SortResult6.sort(rentHouses, variation);
		return JsonResult.success(rentHouses, "OK!");
	}
}
