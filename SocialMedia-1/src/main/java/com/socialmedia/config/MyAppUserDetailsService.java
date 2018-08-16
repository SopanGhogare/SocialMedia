package com.socialmedia.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.socialmedia.dao.UserDAO;
import com.socialmedia.entity.User;

@Service
public class MyAppUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User activeUser = userDAO.getActiveUser(userName);
		GrantedAuthority authority = new SimpleGrantedAuthority(activeUser.getRole());
		UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(
				activeUser.getUserName(), activeUser.getPassword(), Arrays.asList(authority));
		return userDetails;
	}
}
