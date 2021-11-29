package com.makersacademy.acebook.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class UserTest {
    String username = "TestUsername";
    String password = "TestPassword";
    User user = new User(username, password);

    @Test
    public void getUsernameTest(){
        assertEquals( user.getUsername(), username );
    }

    @Test
    public void getPasswordTest(){
        assertEquals( user.getPassword(), password );
    }

    @Test
    public void setUsername(){
        String setUser = "setUsername";
        user.setUsername(setUser);
        assertEquals(user.getUsername(), setUser);
    }
}
