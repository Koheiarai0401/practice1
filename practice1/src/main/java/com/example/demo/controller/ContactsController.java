package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ContactsController {
	@GetMapping("/admin/contacts")
	public String view() {

		return "/admin/contacts";
	}
}