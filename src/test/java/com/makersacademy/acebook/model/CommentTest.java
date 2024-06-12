package com.makersacademy.acebook.model;

import org.junit.Assert;
import org.junit.Test;

public class CommentTest {
    @Test
    public void commentHasContent() {
        Comment comment = new Comment("This is a comment");
        Assert.assertEquals(comment.getContent(), "This is a comment");
    }
}
