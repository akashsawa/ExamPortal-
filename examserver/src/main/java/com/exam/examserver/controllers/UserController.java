package com.exam.examserver.controllers;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.examserver.models.Role;
import com.exam.examserver.models.User;
import com.exam.examserver.models.UserRole;
import com.exam.examserver.services.UserService;
import java.util.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")  //for accessing all the ports for angular and backend
public class UserController {

	@Autowired
	private UserService userService;
	//for creating user
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception
	{
		user.setProfile("default.png");
		Set<UserRole>roles=new HashSet<UserRole>();
		Role role=new Role();
		role.setRoleId(60L);
		role.setRoleName("NORMAL");
	
		UserRole userRole=new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		roles.add(userRole);
		userService.createUser(user, roles);
		return user;
	}
	
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		
		return userService.getUser(username);
	}
	
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") Long userId)
	{
		userService.deleteUser(userId);
	}
}
