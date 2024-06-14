package com.makersacademy.acebook.model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostTest {

	private User user;
	private Post postWithImage;
	private Post postWithoutImage;

	@Before
	public void setUp(){
		user = mock(User.class);
		when(user.getUsername()).thenReturn("testuser");
		postWithoutImage = new Post("hello", user);
		postWithImage = new Post("hello with image", user, "http://image.url/test.jpg");
	}

	@Test
	public void postHasContent() {
		assertThat(postWithoutImage.getContent(), containsString("hello"));
	}

	@Test
	public void postHasUser() {
		assertEquals("testuser", postWithoutImage.getUser().getUsername());
	}

	@Test
	public void postWithImageHasContent() {
		assertThat(postWithImage.getContent(), containsString("hello with image"));
	}

	@Test
	public void postWithImageHasUser() {
		assertEquals("testuser", postWithImage.getUser().getUsername());
	}

	@Test
	public void postWithImageHasImageUrl() {
		assertEquals("http://image.url/test.jpg", postWithImage.getImageUrl());
	}

	@Test
	public void postWithoutImageDoesNotHaveImageUrl() {
		assertNull(postWithoutImage.getImageUrl());
	}

	@Test
	public void postHasCreatedAtTimestampWhenCreated() {
		Post newPost = new Post("timestamped post", user);
		assertNotNull(newPost.getCreatedAt());
		assertTrue(ChronoUnit.SECONDS.between(newPost.getCreatedAt(), LocalDateTime.now()) < 1);
	}
}