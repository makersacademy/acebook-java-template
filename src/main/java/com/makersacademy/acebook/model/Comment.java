package com.makersacademy.acebook.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "COMMENTS")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long postid;
    private Long userid;
    private String content;
    private Timestamp date;

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

    public Comment(Long id, Long postid, Long userid, String content) {
        this.id = id;
        this.postid = postid;
        this.userid = userid;
        this.content = content;
    }

    public Comment(Long id, Long postid, Long userid, String content, Timestamp date) {
        this.id = id;
        this.postid = postid;
        this.userid = userid;
        this.content = content;
        this.date = date;
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

    public Timestamp getDate() {
        return this.date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
