package com.example.demo.service;

import java.util.Collection;
import java.util.Collections;

import org.apache.catalina.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {
    private User user;  // Userオブジェクトを保持します。

    // コンストラクタでUserオブジェクトを受け取り、それをこのクラスのuserにセットします。
    public UserPrincipal(User user) {
        this.user = user;
    }
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public String getPassword() {
		// TODO 自動生成されたメソッド・スタブ
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO 自動生成されたメソッド・スタブ
		return user.getUsername();
	}
	
    // アカウントが有効期限切れでないことを示すために、常にtrueを返します。
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // アカウントがロックされていないことを示すために、常にtrueを返します。
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 資格情報（ここではパスワード）が有効期限切れでないことを示すために、常にtrueを返します。
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // アカウントが有効であることを示すために、常にtrueを返します。
    @Override
    public boolean isEnabled() {
        return true;
    }

}
