package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class PostTest {

	private Post post = new Post("hello");

	@Test
	public void postHasContent() {
		assertThat(post.getContent(), containsString("hello"));
	}

	@Test
	public void postHasTimestamp() {
		LocalDate currentDate = LocalDate.now();
		String dateString = currentDate.toString();
		String timeString = post.getTimeStamp().toString();
		assertThat((timeString), containsString(dateString));
	}
}
