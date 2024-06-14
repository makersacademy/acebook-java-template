package com.makersacademy.acebook.model;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

public class LikeCommentTest {
    public User user;
    public Comment comment;
    public LikeComment like;

    @Before
    public void setUp() {
        user = mock(User.class);
        when(user.getId()).thenReturn(1L);
        comment = mock(Comment.class);
        when(comment.getId()).thenReturn(1L);
        like = new LikeComment(comment, user);
    }

    @Test
    public void likeHasPost() {
        assertEquals(like.getComment(), comment);
    }

    @Test
    public void likeHasUser() {
        assertEquals(like.getUser(), user);
    }
}
