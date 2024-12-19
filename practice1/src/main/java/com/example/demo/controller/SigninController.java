package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.SigninForm;
import com.example.demo.service.SigninService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SigninController {

    private final SigninService service;

    /**
     * サインインページの表示
     */
    @GetMapping("/signin")
    public String view(Model model) {
        model.addAttribute("signinForm", new SigninForm()); // 空のフォームを初期化
        return "signin"; // signin.htmlを表示
    }

    @PostMapping("/signin")
    public String login(@ModelAttribute SigninForm signinForm, Model model) {
        var signin = service.searchSigninByEmail(signinForm.getEmail());
        
        var isCorrectUserAuth = signin.isPresent() 
                && signinForm.getPassword().equals(signin.get().getPassword());
        
        if (isCorrectUserAuth) {
            return "redirect:/menu"; // ログイン成功 → メニュー画面へリダイレクト
        } else {
            model.addAttribute("errorMsg", "メールアドレスとパスワードの組み合わせが間違っています");
            model.addAttribute("signinForm", signinForm); // フォーム情報を再セット
            return "signin"; // signin.htmlを再表示
        }
    }
}