package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

public class UserTest {

    public User user;

    @Before
    public void setUp() {
        user = new User("barry manilow", "copacabana",
                true, "pic.jpg", new Timestamp(10000));
    }

    @Test
    public void userHasUsername() {
        assertEquals(user.getUsername(), "barry manilow");
    }

    @Test
    public void userHasPassword() {
        assertEquals(user.getPassword(), "copacabana");
    }

    @Test
    public void userIsEnabled() {
        assert(user.isEnabled());
    }

    @Test
    public void userHasProfilePicture() {
        assertEquals(user.getProfilePicture(), "pic.jpg");
    }

    @Test
    public void userHasCreatedAt() {
        assertEquals(user.getCreatedAt(), new Timestamp(10000));
    }

}
