package com.makersacademy.acebook.model;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class PostTest {

	private Post post = new Post("hello", "cat_image.jpg");

	@Test
	public void postHasContent() {
		assertThat(post.getContent(), containsString("hello"));
	}

	@Test
	public void postHasTimestamp() {
		post.generateTimestamp();
		LocalDate currentDate = LocalDate.now();
		String dateString = currentDate.toString();
		String timeString = post.getTimestamp().toString();
		assertThat((timeString), containsString(dateString));
	}

	// @Test
	// public void postHasUserId(){
	// 	post.addUserID(1);
	// 	assertEquals("retrieves user id", Integer.valueOf(1), post.getUserId());
	// }

	@Test
	public void postHasImage() {
		assertThat(post.getImagepath(), containsString("cat_image.jpg"));
	}
}
