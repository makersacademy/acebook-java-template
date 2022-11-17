package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import java.sql.Timestamp;
import javax.persistence.Column;
import org.junit.Test;

public class PostTest {

	private Post post = new Post("hello", 0);

	@Test
	public void postHasContent() {
		assertThat(post.getContent(), containsString("hello"));
	}

	@Test 
	public void test_InitialNumberofLikes() {
		Post post = new Post();
    	assertEquals(post.getLikes(), 0);
  }

  	@Test
	public void test_AddstoLikesCounter() {
		Post post = new Post();			
		post.addLikes();
		assertEquals(post.getLikes(), 1);
	}


}
