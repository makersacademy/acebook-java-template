package com.makersacademy.acebook.model;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class PostTest {
    private LocalDateTime testDate = LocalDateTime.now();
    private String content = "content";
    private String username = "username";
    private Post post = new Post("hello");

    @Test
    public void postHasContent() {
        assertThat(post.getContent(), containsString("hello"));
    }

    @Test
    public void postHasDate() {
        post.setTime(LocalDateTime.now());
        assertThat(post.getDate(), instanceOf(LocalDateTime.class));
    }

    @Test
    public void postHasFormattedDate() {
        post.setTime(LocalDateTime.now());
        assertThat(post.getFormattedDate(), instanceOf(String.class));
    }

    @Test
    public void getContentTest() {
        post.setContent(content);
        assertEquals(post.getContent(), content);
    }

    @Test
    public void populateTest() {
        post.populate(content, testDate, username, 0);
        assertEquals(post.getContent(), content);
        assertEquals(post.getDate(), testDate);
        assertEquals(post.getUsername(), username);
        assertEquals(post.getLikes(), 0);
    }
}

