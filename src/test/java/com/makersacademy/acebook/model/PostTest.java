package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class PostTest {

	private Post post = new Post("hello");

	@Test
	public void postHasContent() {
		assertThat(post.getContent(), containsString("hello"));
	}

  @Test
	public void setPostDate() {
    Date date = new Date();
    post.setTime_posted(date);
		assertEquals(post.getTime_posted(), date);
	}

  @Test
	public void setPostUser() {
    post.setUser_id(1);
		assertEquals(post.getUser_id(), Integer.valueOf(1));
	}

}
