package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Signin;

@Repository
public interface SigninRepository extends JpaRepository<Signin, String>{
	Signin findByUsername(String username);
}