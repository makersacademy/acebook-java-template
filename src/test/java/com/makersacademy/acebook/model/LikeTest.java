package com.makersacademy.acebook.model;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

public class LikeTest {
    public User user;
    public Post post;
    public Like like;

    @Before
    public void setUp() {
        user = mock(User.class);
        when(user.getId()).thenReturn(1L);
        post = mock(Post.class);
        when(post.getId()).thenReturn(1L);
        like = new Like(post, user);
    }

    @Test
    public void likeHasPost() {
        assertEquals(like.getPost(), post);
    }

    @Test
    public void likeHasUser() {
        assertEquals(like.getUser(), user);
    }
}
