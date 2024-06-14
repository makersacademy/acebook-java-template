package com.makersacademy.acebook.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class CommentTest {

    private Comment comment;
    private Post post;
    private User user;

    @BeforeEach
    public void setup() {
        // Mock User
        user = mock(User.class);
        user.setUsername("testuser");

        // Create Post
        post = new Post("Test post content", user);

        // Create Comment
        comment = new Comment("Test comment content", post, user);
    }

    @Test
    public void testCommentContent() {
        assertEquals("Test comment content", comment.getContent());
    }

    @Test
    public void testCommentPost() {
        assertEquals(post, comment.getPost());
    }

    @Test
    public void testCommentUser() {
        assertEquals(user, comment.getUser());
    }
}
