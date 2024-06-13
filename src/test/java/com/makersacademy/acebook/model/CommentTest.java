package com.makersacademy.acebook.model;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CommentTest {




    @Test
    public void testCommentCreation() {
        Post post = new Post();
        User user = new User();
        Comment comment = new Comment();
        comment.setContent("Test content");
        comment.setPost(post);
        comment.setUser(user);

        assertNotNull(comment);
        assertEquals("Test content", comment.getContent());
    }





}

