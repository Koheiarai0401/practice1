package com.example.demo.controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Signup;
import com.example.demo.form.SignupForm;
import com.example.demo.repository.SignupRepository;

@Controller
public class SignupController {
    @Autowired
    private SignupRepository signupRepository;

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("signupForm", new SignupForm());
        return "signup";
        
    }
    
    @PostMapping("/signup")
	public String signup(@Validated @ModelAttribute ("signupForm") SignupForm signupForm, BindingResult errorResult, HttpServletRequest request)  {
		
        if (errorResult.hasErrors()) {
            return "signup";
            
        }

		HttpSession session = request.getSession();
        session.setAttribute("signupForm", signupForm);
        
        return "redirect:/signup/confirm1";
	}
    
    @GetMapping("/signup/confirm1")
    public String confirm1(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();

        SignupForm signupForm = (SignupForm) session.getAttribute("signupForm");
        model.addAttribute("signupForm", signupForm);
        return "confirmation1";
    }
    
    @PostMapping("/signup/register")
    public String register(Model model, HttpServletRequest request) {
    	
    	HttpSession session = request.getSession();
    	SignupForm signupForm = (SignupForm) session.getAttribute("signupForm");
    	Signup signup = new Signup();
    	signup.setLastName(signupForm.getLastName());
    	signup.setFirstName(signupForm.getFirstName());
    	signup.setEmail(signupForm.getEmail());
    	signup.setPassword(signupForm.getPassword());
    	
    	signupRepository.save(signup);
    	
    	return "redirect:/signup/complete";
    }
    
    @GetMapping("/signup/complete")
    public String complete(Model model, HttpServletRequest request) {

        if (request.getSession(false) == null) {
          return "redirect:/signup";
        }
    HttpSession session = request.getSession();
    SignupForm signupForm = (SignupForm) session.getAttribute("signupForm");
    model.addAttribute("signupForm", signupForm);
    
    session.invalidate();
    
    return "completion1";
    }
			
}
