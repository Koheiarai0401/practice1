package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration //@Beanを有効化するもの
@EnableWebSecurity  //Bean定義が自動で有効化される
public class SecurityConfig {
	
    @Autowired
    private DataSource dataSource;
    
    @Bean //戻り値がSecurityFilterChain型
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        	.csrf()
        	.and()
	            .authorizeHttpRequests()
	            	.requestMatchers("/admin/signin").permitAll()//  認証不要
	            	.anyRequest().authenticated()//  他のURLはログイン後アクセス可能
            .and()
	            .formLogin()
	                .loginPage("/admin/signin")
	                .usernameParameter("email")
	                .passwordParameter("password")
	                .defaultSuccessUrl("/admin/contact", true)
	                .failureUrl("/admin/signin?error")
            .and()
            	.logout()
            	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            	.logoutUrl("/logout") //ログアウトのURL
            	.invalidateHttpSession(true)
                //ログアウト時の遷移先 POSTでアクセス
                .logoutSuccessUrl("/admin/signin");
            ;
        return http.build();
    }
}
