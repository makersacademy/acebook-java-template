package com.makersacademy.acebook.model;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

public class PostTest {
    private LocalDateTime testDate = LocalDateTime.now();
	private Post post = new Post("hello");

	@Test
	public void postHasContent() {
		assertThat(post.getContent(), containsString("hello"));
	}
	@Test
	public void postHasDate() { post.setTime(LocalDateTime.now()); assertThat(post.getDate(), instanceOf(LocalDateTime.class)); }
	@Test
	public void postHasFormattedDate() { post.setTime(LocalDateTime.now()); assertThat(post.getFormattedDate(), instanceOf(String.class)); }
}
