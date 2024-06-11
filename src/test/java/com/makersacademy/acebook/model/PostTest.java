package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class PostTest {

	private User user;
	private Post post;

	@Before
	public void setUp(){
		user = mock(User.class);
		when(user.getUsername()).thenReturn("testuser");
		post = new Post("hello", user);
	}

	@Test
	public void postHasContent() {
		assertThat(post.getContent(), containsString("hello"));
	}

	@Test
	public void postHasUser() {
		assertEquals("testuser", post.getUser().getUsername());
	}
}
