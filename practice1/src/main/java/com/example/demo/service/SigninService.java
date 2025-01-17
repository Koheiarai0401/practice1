//package com.example.demo.service;
//
//import jakarta.validation.constraints.Email;
//
//import org.apache.catalina.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.example.demo.entity.Signin;
//import com.example.demo.repository.SigninRepository;
//
//@Service
//public class SigninService {
//	@Autowired
//	private SigninRepository signinRepository;
//	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	
//    @Override // UserDetailsServiceインターフェースのメソッドを上書きします
//    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
//        Email email = signinRepository.findByUsername(email); // ユーザー名でユーザーを検索します
//        if (email == null) {
//            throw new UsernameNotFoundException("User not found"); // ユーザーが見つからない場合、例外をスローします
//        }
//        return new UserDetailsServiceImpl(email); // ユーザーが見つかった場合、UserDetailsServiceImplを作成し返します
//    }
//
//    //新たにメソッドを追加します
//    public User findByEmail(String email) {
//        return signinRepository.findById(email); // ユーザー名でユーザーを検索し返します
//    }
//    
//	public Signin createSignin(String email, String rawPassword) {
//		Signin signin = new Signin();
//		signin.setEmail(email);
//		
//		signin.setPassword(passwordEncoder.encode(rawPassword));
//		return signinRepository.save(signin);
//	}
//}
