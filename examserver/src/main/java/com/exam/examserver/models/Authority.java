package com.exam.examserver.models;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

	private String authority;
	
	
	
	public Authority() {
		super();
	}



	public Authority(String authority) {
		super();
		this.authority = authority;
	}



	@Override
	public String getAuthority() {
		return null;
	}

}
