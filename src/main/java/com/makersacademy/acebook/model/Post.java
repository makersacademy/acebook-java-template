package com.makersacademy.acebook.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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
    // private String userWhoPosted;
    private Timestamp timestamp;

    public Post() {}

    public Post(String content) {
        this.content = content;
        this.timestamp = getTime();
    }


    public Timestamp getTime() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat formatTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatTimestamp.format(timestamp);
        return timestamp;
    }

    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }

    public Timestamp getTimeStamp() { return this.timestamp; }

    // public String getUser() { return this.userWhoPosted; }
    // public void setUser(String userWhoPosted) { this.userWhoPosted = userWhoPosted; }

}
