package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Contacts;
import com.example.demo.service.ContactsService;
@Controller
public class ContactsController {
	
	@Autowired
	private ContactsService contactsService;
	
	@GetMapping(value = "/admin/contacts")
	public String displayList(Model model) {
		List<Contacts> contactslist = contactsService.searchAll();
		model.addAttribute("contactslist", contactslist);
		return "admin/contacts";
	}
	
	@GetMapping("/admin/{id}")
	public String displayView(@PathVariable Long id, Model model) {
//		Contacts contacts = contactsService.findById(id);
//		model.addAttribute("contactsData", contacts);
		return "admin/view";
	}
}