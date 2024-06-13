package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class PostTest {

	private User user = new User("daniel", "daniel", "images/foo.jpg", new Timestamp(10000));
	private Post post = new Post("hello", user, "asd", new Timestamp(10000));


	@Test
	public void postHasContent() {

		assertThat(post.getContent(), containsString("hello"));
	}

}
