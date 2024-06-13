package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

public class PostTest {

	public User user;
	public Post post;
	public Post noImagePost;

	@Before
	public void setUp() {
		user = mock(User.class);
		when(user.getUsername()).thenReturn("barry manilow");
		post = new Post("hello", user, "photo", new Timestamp(10000));
		noImagePost = new Post("hello", user, null, new Timestamp(10000));
	}

	@Test
	public void postHasContent() {
		assertThat(post.getContent(), containsString("hello"));
	}

	@Test
	public void postHasUser() {
		assertEquals("barry manilow", post.getUser().getUsername());
	}

	@Test
	public void postHasPhoto() {
		assertEquals("photo", post.getPhoto());
	}

	@Test
	public void postHasTimestamp() {
		assertEquals(new Timestamp(10000), post.getCreatedAt());
	}

	@Test
	public void postWithoutImageDoesNotHaveImageUrl() {
		assertNull(noImagePost.getPhoto());
	}

}
