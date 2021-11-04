package com.makersacademy.acebook.model;


import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import com.github.javafaker.Faker;

public class UserTest {

  Faker faker;

  @Before
    public void setup() {
        faker = new Faker();
        
    }
    
    @Test
	  public void testUser() {
      String name = faker.name().firstName();
      User user = new User(name, "password1", true);
      // test name and password is recorded and returned via getters
      assertThat("test name is recorded", user.getUsername(), containsString(name));
      assertThat("test password is recorded", user.getPassword(), containsString("password1"));
    }

    @Test
	  public void testUserSetters() {
      String name = faker.name().firstName();
      //initialise an empty user
      User user = new User();
      // test name and password is set via setters and and returned via getters
      user.setUsername(name);
      user.setPassword("password2");
      assertThat("test name is set", user.getUsername(), containsString(name));
      assertThat("test password is set", user.getPassword(), containsString("password2"));
    }

  
}
