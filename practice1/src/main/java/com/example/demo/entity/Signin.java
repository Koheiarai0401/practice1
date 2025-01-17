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
	
    private String username;
    
	private String password;
	
    public String getPassword() {
        return password;  // パスワードを返す
    }

    public void setPassword(String password) {
        this.password = password;  // パスワードを設定する
    }

    public String getEmail() {
        return email;  // メールアドレスを返す
    }

    public void setEmail(String email) {
        this.email = email;  // メールアドレスを設定する
    }
}