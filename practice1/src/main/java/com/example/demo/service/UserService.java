package com.example.demo.service;

import jakarta.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Signup;
import com.example.demo.repository.SignupRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	String hashedPassword = encoder.encode("yourPlainPassword");
	
    private final SignupRepository signupRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void create(String email, String rawPassword) {
        var encodedPassword = passwordEncoder.encode(rawPassword);
        
        // Signupエンティティを作成して保存
        Signup signup = new Signup();
        signup.setEmail(email);
        signup.setPassword(encodedPassword);
        
        signupRepository.save(signup); // 自動的にINSERTが実行されます⇨insertの代わりにsaveを実行
    }
}
