package com.socialmedia.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {
    
    /**
     * This Method will generate the password
     * */
	public static void main(String[] args) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println("" + passwordEncoder.encode("password"));
	}

}
