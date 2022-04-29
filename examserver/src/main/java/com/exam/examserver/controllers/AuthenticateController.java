package com.exam.examserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.examserver.config.JwtUtil;
import com.exam.examserver.config.UsernamePasswordAuthenticationFilter;
import com.exam.examserver.models.JwtRequest;
import com.exam.examserver.services.impl.UserDetailsServiceImpl;

@RestController
public class AuthenticateController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtils;
	
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
		try {
			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
		}catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("USER NOT FOUND !....");
		}
		
		UserDetails userDetails= userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token=this.jwtUtils.generateToken(userDetails);
		return ResponseEntity.ok(token);
	
	}
	
	//for authenticate
	private void authenticate(String username,String password) throws Exception {
		
		try {
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
			
		}catch(DisabledException e){
			e.printStackTrace();
			throw new Exception("USER IS DISABLED !...");
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("INVALID CREDENTAILS!...."+e.getMessage());
		}
		
	}
	
}
