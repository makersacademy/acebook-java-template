package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Test;

public class PostTest {
	@Test
	public void postHasContent() {
		assertThat(post.getContent(), containsString("hello"));
	}

}
