package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import org.junit.Assert;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
  public void testUsernameSetter() {
    String name = faker.name().firstName();
    // initialise an empty user
    User user = new User();
    // test name and password is set via setters and and returned via getters
    user.setUsername(name);
    // user.setPassword("password2");
    assertThat("test name is set", user.getUsername(), containsString(name));
    // assertThat("test password is set", user.getPassword(),
    // containsString("password2"));
  }

  @Test
  public void testEncodingPassword() {
    String rawPassword = "java2021";

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String encodedPassword = passwordEncoder.encode(rawPassword);

    boolean matched = passwordEncoder.matches("java2021", encodedPassword);

    Assert.assertTrue(matched);

  }

  @Test
  public void doesntMatchSameValue() {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    assertFalse(passwordEncoder.matches("password", "password"));
  }

  @Test
  public void doesntMatchNullEncodedValue() {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    assertFalse(passwordEncoder.matches("password", null));
  }

  @Test
  public void doesntMatchEmptyEncodedValue() {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    assertFalse(passwordEncoder.matches("password", ""));
  }

  @Test
  public void doesntMatchBogusEncodedValue() {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    assertFalse(passwordEncoder.matches("password", "012345678901234567890123456789"));
  }

}
