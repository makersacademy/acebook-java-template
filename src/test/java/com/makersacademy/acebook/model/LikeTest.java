package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class LikeTest {

	private Like like = new Like();

  @Test
	public void setPostId() {
        like.setPost_id(1);
        assertEquals(like.getPost_id(), 1);
    }

  @Test
	public void setUserId() {
        // Long userId = (long) 1;
        like.setUser_id(1);
		assertEquals(like.getUser_id(), 1);
	}
}
