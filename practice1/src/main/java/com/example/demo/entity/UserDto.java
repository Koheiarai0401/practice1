package com.example.demo.entity;
import jakarta.validation.constraints.NotEmpty;

public class UserDto {
	
	@NotEmpty
	private String username;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String email;
	
    public String getUsername() {
        return username;  // ユーザー名を返す
    }

    public void setUsername(String username) {
        this.username = username;  // ユーザー名を設定する
    }

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
