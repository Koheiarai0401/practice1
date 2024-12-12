package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.LoginForm;
import com.example.demo.service.LoginService;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class LoginController {
	private final LoginService service;
	@GetMapping("/loginpage")
	public String view (Model model, LoginForm form) {
		return "loginpage";
	}
	
	@PostMapping("/loginpage")
	public String loginpage(Model model, LoginForm form) {
		var Admins =service.searchAdminsById(form.getEmail());
		var isCorrectUserAuth = Admins.isPresent()
				&& form.getPassword().equals(Admins.get().getPassword());
		if (isCorrectUserAuth) {
			return "redirect:/menu";
		} else {
			model.addAttribute("errorMsg", "メールアドレスとパスワードの組み合わせが間違っています");
			return "loginpage";
		}
	}

}
