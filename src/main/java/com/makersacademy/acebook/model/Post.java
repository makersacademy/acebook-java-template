package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;

import lombok.Data;

import java.io.Console;
import java.sql.Date;
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

//    this.postTime = LocalDateTime.now();

    public String getContent() {
        return this.content;
    }

    public void addTime() {
        LocalDateTime timeIn = LocalDateTime.now();
        this.postTime = timeIn;
    }

    public LocalDateTime getDate() {
        return this.postTime;
    }

    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yy kk:mm");
        String formattedDateTime;

        if (postTime != null) {
        formattedDateTime = postTime.format(formatter);
        }
        else {
            formattedDateTime = "Not available";
        }

        return formattedDateTime;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
