package com.example.demo.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.repository.SignupRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	String hashedPassword = encoder.encode("yourPlainPassword");
	
    private final SignupRepository signupRepository;
    private final PasswordEncoder passwordEncoder;
}
