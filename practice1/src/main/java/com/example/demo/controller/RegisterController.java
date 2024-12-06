package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.User;
import com.example.demo.entity.UserDto;
import com.example.demo.service.UserService;

@Controller
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
	public ModelAndView registerForm() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", new UserDto());
		mav.setViewName("register");
		return mav;
	}
	
    @PostMapping("/register") // "/register"というURLに対するPOSTリクエストを処理します
    public String register(@ModelAttribute UserDto userDto) {
        User existing = userService.findByUsername(userDto.getUsername()); // ユーザー名で既存のユーザーを検索します
        if(existing != null){
            // ユーザーが既に存在する場合の処理
            return "register"; // ユーザーが存在するため、再度登録画面を表示します
        }
        userService.save(userDto); // ユーザーが存在しない場合、新しいユーザーを保存します
        return "login"; // 登録が成功した場合、ログイン画面を表示します
    }
}
