package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Signin;
import com.example.demo.repository.SigninRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private SigninRepository signinRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Signin signin = signinRepository
                .findById(email)
                .orElseThrow(() -> {
                	System.out.println("Email:" + email + "が見つかりません。");
                	throw new UsernameNotFoundException(email);
                });

        return User.withUsername(
        		signin.getEmail())
                .password(signin.getPassword())
                .build();
        }

}
