package com.makersacademy.acebook.model;
import com.makersacademy.acebook.service.IPostService;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import java.beans.Transient;

import org.junit.Test;

public class PostTest {

	private Post post = new Post("hello","Susan");

	@Test
	public void postHasContent() {
		assertThat(post.getContent(), containsString("hello"));
	}



	// @Test 
	// public void likepost(){
	// 	post.like();
	// 	assertEquals(post.getLikes(), Integer.valueOf(1));
	// }

}
