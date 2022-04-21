package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Test;

public class PostTest {

	private Post post = new Post("hello");

	@Test
	public void postHasContent() {
		assertThat(post.getContent(), containsString("hello"));
	}

	@Test 
	public void testGetsInitialNumberofLikes() {
		Post post = new Post();
    assertEquals(post.likeCount(), Integer.valueOf(0));
  }

	@Test
	public void testAddstoLikeCounter() {
		Post post = new Post();
		post.addLike();
		assertEquals(post.likeCount(), Integer.valueOf(1));
	}
}
