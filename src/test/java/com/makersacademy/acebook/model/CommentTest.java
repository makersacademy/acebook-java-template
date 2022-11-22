package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CommentTest {

    Long post_id = (long) 4;
    Long user_id = (long) 1;
    private Comment comment = new Comment("first comment");

    @Before
    public void setup() {
        comment.setPostId(post_id);
        comment.setUserId(user_id);
    }

    @After
    public void tearDown() {
    }

	@Test
	public void commentHasContent() {
		assertThat(comment.getContent(), containsString("first comment"));
	}

    @Test
    public void commentHasUserID(){
        assertEquals(Long.valueOf(1), comment.getUserID());
    }

    @Test
    public void commentHasPostID(){
        assertEquals(Long.valueOf(4), comment.getPostID());
    }
}

