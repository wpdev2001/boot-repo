package com.wp.ormdemo;

import com.wp.ormdemo.entities.User;
import com.wp.ormdemo.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(Application.class);

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		SAVE USER
//		User user = new User();
//		user.setName("Piyush");
//		user.setCity("Pune");
//		user.setAge(29);
//		User savedUser = userService.saveUser(user);
//		System.out.println(savedUser);

//		GET SINGLE USER
//		User userById = userService.getUserById(20);
//		logger.info("user with id : {} is {}",20,userById);

//		GET ALL USERS
//		List<User> allUser = userService.getAllUser();
//		logger.info("user size is : {}", allUser.size());
//		logger.info("Users: {}", allUser);

//		UPDATE USER
//		User userWithNewDetails = new User();
//		userWithNewDetails.setName("Shreyash");
//		userWithNewDetails.setCity("mumbai");
//		userWithNewDetails.setAge(30);
//		User updatedUser = userService.updateUser(userWithNewDetails, 1);
//		logger.info("updated user details: {}", updatedUser);

//		DELETE USER
		userService.deleteUser(2);

	}
}
