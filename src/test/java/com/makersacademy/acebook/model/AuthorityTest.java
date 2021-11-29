package com.makersacademy.acebook.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AuthorityTest {
    @Test
    public void authorityTest(){
        String username = "Username";
        String password = "Password";
        Authority authority = new Authority(username,password);
        assertTrue(authority instanceof Authority);
    }
}
