package com.makersacademy.acebook.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    private Timestamp timestamp;

    public Post() {}

    public Post(String content, Timestamp timestamp) {
        this.content = content;
        this.timestamp = timestamp;
    }
    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }
    public Timestamp getTimestamp() { return this.timestamp; }
    
    public void generateTimestamp() {
        long now = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(now);
        this.timestamp = timestamp;
    }

    // public static Timestamp createTimeStamp() {
    //     long now = System.currentTimeMillis();
    //     Timestamp timestamp = new Timestamp(now);
    //     return timestamp;
    // }

    // private String createTimeStamp() {
    //     LocalDateTime currentDateTime = LocalDateTime.now();
    //     DateTimeFormatter formattedTimeObj = DateTimeFormatter.ofPattern("yyyy MM dd HH:mm:ss");
    //     String timeStamp = currentDateTime.format(formattedTimeObj);
    //     return timeStamp;
    // }

}
