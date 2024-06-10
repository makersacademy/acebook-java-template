package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Test;

public class PostTest {
	User user = new User();

	private Post post = new Post("hello", "Greetings!", user);

	@Test
	public void postHasContent() {
		assertThat(post.getContent(), containsString("hello"));
	}

}
