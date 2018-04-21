package com.pibigstar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String toIndex() {
		return "index";
	}
	@RequestMapping("/toMapbin")
	public String toMapbin() {
		return "mapbin";
	}

}
