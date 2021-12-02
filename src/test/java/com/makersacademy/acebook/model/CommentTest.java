package com.makersacademy.acebook.model;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class CommentTest {
    private Comment comment = new Comment();

    @Test
    public void getCommentContent() {
        comment.setContent("Spring Boob is hard!");
        assertThat(comment.getContent(), containsString("Spring Boob is hard!"));
    }
    @Test
    public void getUserName() {
        comment.setUsername("Jana Dark");
        assertThat(comment.getUsername(), containsString("Jana Dark"));
    }
    @Test
    public void getCommentTime(){
        comment.setStamp( LocalDateTime.now());
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 'at' HH:mm");
        String formatDateTime = time.format(formatter);
        assertThat(comment.getStamp(), containsString(formatDateTime));
    }
}
