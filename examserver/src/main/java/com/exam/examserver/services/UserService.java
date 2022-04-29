package com.exam.examserver.services;

import java.util.Set;

import com.exam.examserver.models.User;
import com.exam.examserver.models.UserRole;

public interface UserService {
	
	//creating user
	public User createUser(User user,Set<UserRole>userRoles) throws Exception;
	//getuser
	public User getUser(String username);
	public void deleteUser(Long userId);
}
