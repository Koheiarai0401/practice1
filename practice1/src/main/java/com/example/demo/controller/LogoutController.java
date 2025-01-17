package com.example.demo.controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

	// ログアウト処理用のハンドラをDI注入する
	SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

	// ログアウトコントローラー
	@GetMapping("/logout")
	public String logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
		// ログアウト処理(セッション破棄など)
		this.logoutHandler.logout(request, response, authentication);
		// ログアウト後はログインページにリダイレクトする
		return "redirect:/admin/signin";		
	}
}