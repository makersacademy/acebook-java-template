package com.makersacademy.acebook.model;


import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommentTest {
    User user = new User();
    public Post post;

    private Comment comment;

    @Before
    public void setUp() {
        user = mock(User.class);
        when(user.getUsername()).thenReturn("barry manilow");
        post = new Post("hello", user, "photo", new Timestamp(10000));
        comment = new Comment("This is a comment", user, post, new Timestamp(10000));
    }


    @Test
    public void commentHasContent() {

        assertThat(comment.getContent(), containsString("This is a comment"));
    }
    @Test
    public void commentHasUser() {
        assertEquals("barry manilow", comment.getUser().getUsername());
    }
    @Test
    public void commentHasPost() {
        assertEquals(post, comment.getPost());
    }
    @Test
    public void commentHasTimestamp() {
        assertEquals(new Timestamp(10000), comment.getCreatedAt());
    }

}
