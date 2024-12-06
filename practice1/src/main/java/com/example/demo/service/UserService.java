package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entity.User;
import com.example.demo.entity.UserDto;
import com.example.demo.repository.UserRepository;
public class UserService implements UserDetailsService {
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
    @Override // UserDetailsServiceインターフェースのメソッドを上書きします
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username); // ユーザー名でユーザーを検索します
        if (user == null) {
            throw new UsernameNotFoundException("User not found"); // ユーザーが見つからない場合、例外をスローします
        }
        return new UserPrincipal(user); // ユーザーが見つかった場合、UserPrincipalを作成し返します
    }
    
    public User findByUsername(String username) {
        return userRepository.findByUsername(username); // ユーザー名でユーザーを検索し返します
    }
    
    @Transactional
    public void save(UserDto userDto) {
    	User user = new User ();
    	user.setUsername(userDto.getUsername());
    	
    	user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    	user.setEmail(userDto.getEmail());
    	userRepository.save(user);
    }

}
