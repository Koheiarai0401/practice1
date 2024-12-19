package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Signin;
import com.example.demo.repository.SigninRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SigninService {
	private final SigninRepository repository;
	
    public Optional<Signin> searchSigninByEmail(String Email) {
        return repository.findById(Email);
    }
}
