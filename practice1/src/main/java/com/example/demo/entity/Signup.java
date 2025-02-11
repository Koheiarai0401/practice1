package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "admins")
public class Signup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自動増分IDを指定
	private Long id;
    
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "current_sign_in_at", nullable = true)
    private String current_sign_in_at;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;
    
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updated_at;
    
    @PrePersist
    public void prePersist() {
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updated_at = LocalDateTime.now();
    }
}
