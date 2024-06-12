package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Test;

import java.sql.Timestamp;

public class PostTest {

	private Post post = new Post("hello", 1L, "asd", new Timestamp(10000));



	@Test
	public void postHasContent() {

		assertThat(post.getContent(), containsString("hello"));
	}

}
