
package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

public class CommentTest {

    private Post post = new Post("This is a post.");
    private Comment comment1 = new Comment("Ariane", post.getId(), "I like your post!");
    private Comment comment2 = new Comment("Bob", post.getId(), "Your post is cringe!");

    @Test
    public void commentHasContent() {
        assertThat(comment1.getContent(), containsString("I like your post!"));
    }

    @Test
    public void postStoresComments() {
        List<Comment> comments = new ArrayList<Comment>();
        comments.add(comment1);
        comments.add(comment2);
        post.setComments(comments);
        assertEquals(post.getComments(), comments);
    }

}

