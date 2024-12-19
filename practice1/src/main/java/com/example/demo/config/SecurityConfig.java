//package com.example.demo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf().disable() // CSRF保護を無効化 (必要に応じて有効化)
//            .authorizeHttpRequests(authz -> authz
//                .requestMatchers("/loginpage", "/css/**", "/js/**", "/images/**").permitAll() // ログインページは全員がアクセス可能
//                .anyRequest().authenticated() // その他のリクエストは認証が必要
//            )
//            .formLogin(form -> form
//                .loginPage("/loginpage") // ログインページのURL
//                .loginProcessingUrl("/loginpage")
//                .defaultSuccessUrl("/menu", true) // ログイン成功後のリダイレクト先
//                .permitAll() // ログインページへのアクセスを許可
//            );
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(); // パスワードのハッシュ化にBCryptを使用
//    }
//}