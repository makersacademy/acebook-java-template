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
    private Long id;
    private String content;
    private LocalDateTime postTime;
    private String username;
    private int likes;

    public int getLikes() {
        return likes;
    }

    public void setLikes(int number){
        likes = number;
    }

    public Post() {
    }

    public Post(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void addTime(LocalDateTime current_time) {
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

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

}
