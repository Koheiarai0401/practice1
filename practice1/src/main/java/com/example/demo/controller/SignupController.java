package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Signup; // Admins から Signup に変更
import com.example.demo.form.SignupForm;
import com.example.demo.repository.SignupRepository; // AdminsRepository から SignupRepository に変更

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor // コンストラクタの自動生成
public class SignupController {
    
    private final SignupRepository signupRepository; // フィールドに変更 (final + @RequiredArgsConstructor)
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/admin/signup")
    public String signup(Model model) {
        model.addAttribute("signupForm", new SignupForm());
        return "/admin/signup";
    }
    
    @PostMapping("/admin/signup")
    public String signup(@Validated @ModelAttribute("signupForm") SignupForm signupForm, 
                         BindingResult errorResult, 
                         HttpServletRequest request)  {
        if (errorResult.hasErrors()) {
            return "/admin/signup";
        }

        HttpSession session = request.getSession();
        session.setAttribute("signupForm", signupForm);
        
        return "redirect:/admin/signup/confirm";
    }
    
    @GetMapping("/admin/signup/confirm")
    public String confirm1(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();

        SignupForm signupForm = (SignupForm) session.getAttribute("signupForm");
        model.addAttribute("signupForm", signupForm);
        return "/admin/confirmation";
    }
    
    @PostMapping("/admin/signup/register")
    public String register(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        SignupForm signupForm = (SignupForm) session.getAttribute("signupForm");
      
        var encodedPassword = passwordEncoder.encode(signupForm.getPassword());
        // MySQLのadminsテーブルにデータを挿入
        Signup signup = new Signup();
        signup.setEmail(signupForm.getEmail());
        signup.setPassword(encodedPassword);
        signup.setLastName(signupForm.getLastName());
        signup.setFirstName(signupForm.getFirstName());
        
        signupRepository.save(signup); // 大文字のSignupRepository → 小文字のsignupRepositoryに修正
        
        return "redirect:/admin/signup/complete";
    }
    
    @GetMapping("/admin/signup/complete")
    public String complete(Model model, HttpServletRequest request) {
        if (request.getSession(false) == null) {
            return "redirect:/admin/signup";
        }
        
        HttpSession session = request.getSession();
        SignupForm signupForm = (SignupForm) session.getAttribute("signupForm");
        model.addAttribute("signupForm", signupForm);
        
        session.invalidate();
        
        return "/admin/completion";
    }
}