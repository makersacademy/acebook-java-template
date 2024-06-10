package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Test;

import java.sql.Timestamp;

public class PostTest {

	Timestamp timestamp = Timestamp.valueOf("2007-09-23 10:10:10.0");;

	private Post post = new Post("hello", timestamp, 1L);

	@Test
	public void postHasContent() {
		assertThat(post.getContent(), containsString("hello"));
	}

	@Test
	public void postHasU() {
		assertThat(post.getContent(), containsString("hello"));
	}

}
