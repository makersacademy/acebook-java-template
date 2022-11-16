package com.makersacademy.acebook.model;

import java.util.Date;

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
    private Date time_posted;
    // private String user_id;

    public Post() {}

    public Post(String content) {
        this.content = content;
    }
    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }

    public Date getTime_posted() { return this.time_posted; }
    public void setTime_posted(Date time_posted) { this.time_posted = time_posted; }

    // public String getUser_id() { return this.user_id; }
    // public void setUser_id(String user_id) { this.user_id = user_id; }

}
