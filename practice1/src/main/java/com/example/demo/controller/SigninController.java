package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.SigninForm;
import com.example.demo.service.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SigninController {

    private final UserDetailsServiceImpl service;

    /**
     * サインインページの表示
     */
    @GetMapping("/admin/signin")
    public String detail(Model model) {
        model.addAttribute("signinForm", new SigninForm()); // 空のフォームを初期化
        return "/admin/signin"; // signin.htmlを表示
    }
    
	@RequestMapping("/logout")
    String afterLogout() {
        return "logout";
	}
	
	
}