package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar;  
import java.sql.Timestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private Timestamp time_posted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    public Post() {}

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
    
    public String getTitle() {return this.title;}
    public String getContent() {return this.content;}
    public User getUser() {return user;}
    public Long getId() { return id; }
    public Date getTimePosted() { return time_posted;}

    public void setTitle(String title) {this.title = title;}
    public void setContent(String content) {this.content = content;}
    public void setUser(User user) {this.user = user;}
    public void setTimePosted(Timestamp time_posted) {this.time_posted = time_posted;}
 


}
