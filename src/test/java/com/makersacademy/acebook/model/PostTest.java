package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.*;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PostTest {
    private LocalDateTime testDate = LocalDateTime.now();
	private Post post = new Post("hello");

	@Test
	public void postHasContent() {
		assertThat(post.getContent(), containsString("hello"));
	}
	@Test
	public void postHasDate() { post.addTime(LocalDateTime.now()); assertThat(post.getDate(), instanceOf(LocalDateTime.class)); }
	@Test
	public void postHasFormattedDate() { post.addTime(LocalDateTime.now()); assertThat(post.getFormattedDate(), instanceOf(String.class)); }
}
