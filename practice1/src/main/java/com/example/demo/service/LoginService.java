package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Admins;
import com.example.demo.repository.AdminsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	private final AdminsRepository repository;
	
	public Optional<Admins>searchAdminsById(String email) {
		return repository.findById(email);
	}
}
