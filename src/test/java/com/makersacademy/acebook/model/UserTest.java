package com.makersacademy.acebook.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserTest {
    String username = "TestUsername";
    String password = "TestPassword";
    User user = new User(username, password);

    @Test
    public void getUsernameTest() {
        assertEquals(user.getUsername(), username);
    }

    @Test
    public void getPasswordTest() {
        assertEquals(user.getPassword(), password);
    }

    @Test
    public void setUsername() {
        String setUser = "setUsername";
        user.setUsername(setUser);
        assertEquals(user.getUsername(), setUser);
    }

    @Test
    public void setPassword() {
        String setPass = "setPassword";
        user.setPassword(setPass);
        assertEquals(user.getPassword(), setPass);
    }

    @Test
    public void userTest() {
        User userWithBoolean = new User(username, password, true);
        User userWithNothing = new User();
        assertTrue(userWithBoolean instanceof User);
        assertTrue(userWithNothing instanceof User);
    }
}
