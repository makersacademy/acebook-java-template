package com.makersacademy.acebook.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test
    public void getUsernameTest(){
        String username = "TestUsername";
        String password = "TestPassword";
        User user = new User(username, password);
        assertEquals( user.getUsername(), username );
    }

    @Test
    public void getPasswordTest(){
        String username = "TestUsername";
        String password = "TestPassword";
        User user = new User(username, password);
        assertEquals( user.getPassword(), password );
    }
}
