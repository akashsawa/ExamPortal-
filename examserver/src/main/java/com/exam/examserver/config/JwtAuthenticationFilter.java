package com.exam.examserver.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exam.examserver.services.impl.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtil jwtUtil;  //this clas will have all method for generate expire or not the token
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String requestTokenHeader=request.getHeader("Authorization");
		System.out.println("TOKEN REQUESTED: "+requestTokenHeader);
		String username=null;
		String jwtToken=null;
		

		if(requestTokenHeader !=null && requestTokenHeader.startsWith("Bearer ")) {
			//yes
			jwtToken=requestTokenHeader.substring(7);
			try {
				username=jwtUtil.extractUsername(jwtToken);
			}catch(ExpiredJwtException e) {
				e.printStackTrace();
				System.out.println("JWT TOKEN HAS EXPIRED !...");
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
		}
		else {
			System.out.println("INVALID TOKEN !! NOT STARTS WITH BEARER !!....");
		}
		
		//validated
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails=this.userDetailsServiceImpl.loadUserByUsername(username);
			if(this.jwtUtil.validateToken(jwtToken, userDetails))
			{
				//token is valid
				UsernamePasswordAuthenticationToken usernamePasswordAuthentication=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
			}
		}else {
				System.out.println("TOKEN IS NOT VALID !.....");
			}
			
		  //forwarding the filter request
			filterChain.doFilter(request, response);
		}
		
		
		
}

