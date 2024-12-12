package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class MenuController {
	@GetMapping("/Menu")
	public String view() {
		
		return "menu";
	}
}
