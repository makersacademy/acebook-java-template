package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class PostTest {

	private Post post = new Post("hello");

	@Test
	public void postHasContent() {
		assertThat(post.getContent(), containsString("hello"));
	}

  @Test
	public void setPostDate() {
    LocalDateTime date = LocalDateTime.now();
    DateTimeFormatter new_date = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    String new_date_format = date.format(new_date);
    post.setTime_posted(new_date_format);
		assertEquals(post.getTime_posted(), date);
	}

  @Test
	public void setPostUser() {
    post.setUser_id(1);
		assertEquals(post.getUser_id(), Integer.valueOf(1));
	}

}
