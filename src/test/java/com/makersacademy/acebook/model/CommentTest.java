package com.makersacademy.acebook.model;


import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CommentTest {
    User user = new User();


    private Comment comment;

    @Before
    public void setUp() {
        comment = new Comment("This is a comment", 1L, 1L, new Timestamp(10000));
    }


    @Test
    public void commentHasContent() {

        assertThat(comment.getContent(), containsString("This is a comment"));
    }
    @Test
    public void commentHasUserId() {
        assertEquals(Long.valueOf(1L), comment.getPostId());
    }
    @Test
    public void commentHasPostId() {
        assertEquals(Long.valueOf(1L), comment.getPostId());
    }
    @Test
    public void commentHasTimestamp() {
        assertEquals(new Timestamp(10000), comment.getCreatedAt());
    }

}
