package com.makersacademy.acebook.model;


import org.junit.Assert.*;
import org.junit.Test;

import java.sql.Timestamp;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class CommentTest {
    User user = new User();

    private Comment comment = new Comment("This is a comment", user, 1L, new Timestamp(10000));

    @Test
    public void commentHasContent() {

        assertThat(comment.getContent(), containsString("This is a comment"));
    }

}
