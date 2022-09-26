package com.makersacademy.acebook.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;

import lombok.Data;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Long user_id;
    private Timestamp date;
    private String formatted_date;
    private String formatted_time;
    private String image_post;
    private String username;

    // Constructors:
    public Post() {
    }

    public Post(String content) {
        this.content = content;
    }

    public Post(String content, Long user_id) {
        this.content = content;
        this.user_id = user_id;
    }

    public Post(String content, Long user_id, Timestamp date, String image_post, String username) {
        this.content = content;
        this.user_id = user_id;
        this.date = date;
        this.image_post = image_post;
        this.username = username;
    }

    // Getters and Setters:
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUser_id() {
        return this.user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Timestamp getDate() {
        return this.date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
        this.formatted_time = this.getTimeString();
        this.formatted_date = this.getDateString();
    }

    public String formatDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        return dtf.format(now);
    }

    public String getTimeString() {
        LocalDateTime time = this.date.toLocalDateTime(); // current time
        DateTimeFormatter format_t = DateTimeFormatter.ofPattern("HH:mm"); // format to extract
        String t = time.format(format_t);
        return t;
    }

    public String getDateString() {
        LocalDateTime date = this.date.toLocalDateTime(); // current date
        DateTimeFormatter format_t = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // format to extract
        String t = date.format(format_t);
        return t;
    }

    public String getImagePost() {
        return this.image_post;
    }

    public void setImagePost(String image_post) {
        this.image_post = image_post;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
