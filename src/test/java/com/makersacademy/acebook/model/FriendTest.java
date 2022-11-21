package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class FriendTest {

	private Friend friend = new Friend();

    @Test
	public void setToUser() {
        friend.setToUser(2);
        assertEquals(friend.getToUser(), 2);
    }

    @Test
	public void setFromUser() {
        friend.setFromUser(1);
        assertEquals(friend.getFromUser(), 1);
    }
  
    @Test
	public void setConfirmed() {
        Integer confirmed = 1;
        friend.setConfirmed(confirmed);
        assertEquals(friend.getConfirmed(), confirmed);
    }

}
