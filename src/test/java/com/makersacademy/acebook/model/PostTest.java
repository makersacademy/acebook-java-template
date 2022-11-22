package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PostTest {

	private Post post = new Post("hello", 0);
	Date timestamp = new Date();

	@Before
    public void setup() {
		//Timestamp timestamp = "2022-11-19 00:00:00";
		post.setDate(timestamp);
		post.setUserId((long)1);
    }

    @After
    public void tearDown() {
    }

	@Test
	public void postHasCorrectTimestamp(){
		assertEquals(timestamp, post.getDate());
	}

	@Test
	public void postHasContent() {
		assertThat(post.getContent(), containsString("hello"));
	}

	@Test
	public void postHasUserID(){
		assertEquals(Long.valueOf(1), post.getUser_id());
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
