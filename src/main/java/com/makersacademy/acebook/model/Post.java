package com.makersacademy.acebook.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    private LocalDateTime postTime;
    private String username;
    private int likes;

    public Post() {
    }

    public Post(String content) {
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getLikes() {
        return this.likes;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void setTime(LocalDateTime current_time) {
        LocalDateTime timeIn = current_time;
        this.postTime = timeIn;
    }

    public LocalDateTime getDate() {
        return this.postTime;
    }

    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yy kk:mm");
        return (postTime != null) ? postTime.format(formatter) : "Not available";
    }

    public void populate(String content, LocalDateTime time, String username, int likes) {
        setContent(content);
        setTime(time);
        setUsername(username);
        setLikes(likes);
    }

    public void populate_mock(int id, String content, LocalDateTime time, String username, int likes) {
        setId(id);
        setContent(content);
        setTime(time);
        setUsername(username);
        setLikes(likes);
    }


    public void incrementLikes() {
        likes++;
    }
}
