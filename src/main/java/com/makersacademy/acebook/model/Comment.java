package com.makersacademy.acebook.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "COMMENTS")
public class Comment implements Comparable<Comment> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long postid;
    private Long userid;
    private String content;
    private Timestamp date;
    private String username;
    private String formatted_date;
    private String formatted_time;

    // Constructors:
    public Comment() {
    }

    public Comment(Long id) {
        this.id = id;
    }

    public Comment(Long id, Long postid, Long userid) {
        this.id = id;
        this.postid = postid;
        this.userid = userid;
    }

    public Comment(Long id, Long postid, Long userid, String content, String username) {
        this.id = id;
        this.postid = postid;
        this.userid = userid;
        this.content = content;
        this.username = username;
    }

    public Comment(Long id, Long postid, Long userid, String content, Timestamp date, String username) {
        this.id = id;
        this.postid = postid;
        this.userid = userid;
        this.content = content;
        this.date = date;
        this.username = username;

    }

    // Getters and Setters:
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostid() {
        return this.postid;
    }

    public void setPostid(Long postid) {
        this.postid = postid;
    }

    public Long getUserid() {
        return this.userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public int compareTo(Comment o) {
        if (this.date == null || o.getDate() == null) {
            return 0;
        }
        return this.date.compareTo(o.getDate());
    }

}
