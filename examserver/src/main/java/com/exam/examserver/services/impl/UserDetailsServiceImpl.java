package com.exam.examserver.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exam.examserver.models.User;
import com.exam.examserver.repo.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{  //UserDetailsService for when user s username is sent from browser it will be authorised through this service impl

	private UserRepository userrepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userrepository.findByUsername(username);
		if(user==null) {
			System.out.println("USER NOT FOUND !..");
			throw new UsernameNotFoundException("NO USER FOUND");
		}
		return user;
	}

		
	
	
}
