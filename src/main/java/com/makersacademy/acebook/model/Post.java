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

}
