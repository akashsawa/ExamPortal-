package com.exam.examserver;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.resource.transaction.backend.jta.internal.JtaTransactionCoordinatorImpl.TransactionDriverControlImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.exam.examserver.models.Role;
import com.exam.examserver.models.User;
import com.exam.examserver.models.UserRole;
import com.exam.examserver.services.UserService;

@SpringBootApplication
public class ExamserverApplication {

	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		
		//System.out.println("hi akash");
		SpringApplication.run(ExamserverApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println("starting code ....");
//		User user=new User();
//		user.setFirstName("Sagar");
//		user.setLastname("SAWANT");
//		user.setUsername("Sagar@220798");
//		user.setPassword("abc");
//		user.setEmail("akash@gmail.com");
//		user.setProfile("default.png");
//		
//		Role role=new Role();
//		role.setRoleId(50L);
//		role.setRoleName("ADMIN");
//		
//		Set<UserRole>userRolesSet=new HashSet<UserRole>();
//		UserRole userRole=new UserRole();
//		userRole.setRole(role);
//		userRole.setUser(user);
//		userRolesSet.add(userRole);
//		User newUser=userService.createUser(user, userRolesSet);
//		System.out.println(newUser.getFirstName());
//		
//	}	

}
