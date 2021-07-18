package com.userwallet.wallet.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.userwallet.wallet.entities.Users;

public class AppUser extends Users implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3101067042641786428L;

	public AppUser(Users user) {
		super(user);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		getRoles().forEach(role -> {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		});
		return grantedAuthorities;
	}

	@Override
	public String getUsername() {
		return super.getEmailId();
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}