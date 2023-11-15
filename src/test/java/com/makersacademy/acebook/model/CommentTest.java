package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Test;

public class CommentTest {

    private Post post = new Post("This is a post.");
    private Comment comment = new Comment("Ariane", post.getId(), "I like your post!");

    @Test
    public void commentHasContent() {
        assertThat(comment.getContent(), containsString("I like your post!"));
    }

}
