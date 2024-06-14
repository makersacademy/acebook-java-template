package com.makersacademy.acebook.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class LikeTest {

    private Like like;
    private User user;
    private Post post;

    @BeforeEach
    public void setup() {
        // Mock User
        user = mock(User.class);

        // Create Post
        post = new Post("Test post content", user);

        // Create Like
        like = new Like(user, post);
    }

    @Test
    public void testLikeUser() {
        assertEquals(user, like.getUser());
    }

    @Test
    public void testLikePost() {
        assertEquals(post, like.getPost());
    }

    @Test
    public void testLikeCreatedAt() {
        // Check if the createdAt field is not null
        assertEquals(LocalDateTime.now().getDayOfYear(), like.getCreatedAt().getDayOfYear());
    }
}
