package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "admins")
@Data
public class Signin {
    @Id
	private String email;
	
	private String password;
}
