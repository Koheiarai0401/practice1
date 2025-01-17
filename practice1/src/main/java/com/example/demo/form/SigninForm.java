package com.example.demo.form;

import jakarta.persistence.Id;

import lombok.Data;

@Data
public class SigninForm {
	private String email;
	private String password;
	  @Id
	  private String id;
}
