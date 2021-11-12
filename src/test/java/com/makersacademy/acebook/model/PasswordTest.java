package com.makersacademy.acebook.model;

import static org.junit.Assert.*;

import com.makersacademy.acebook.repository.UserRepository;
import com.makersacademy.acebook.service.IUserService;
import com.makersacademy.acebook.service.UserService;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PasswordTest {

	private User user = new User("Username","Password");

	@Test
	public void passwordIsEncrypted() {
    assertTrue(user.getPassword() == "Password");
    user.setPassword("Password");
    assertFalse(user.getPassword() == "Password");
	}
}
