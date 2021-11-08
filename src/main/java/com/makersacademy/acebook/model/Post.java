package com.makersacademy.acebook.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.GenerationType;

import lombok.Data;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String content;
    public String usern;
    @CreationTimestamp
    public Timestamp time;
    public Post() {}

    public Post(String content, String usern) {
        this.content = content;
        this.usern = usern;
    }

    public String getContent() {
        return this.content;
    }

    public Long getId() {
        return this.id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsern() { return this.usern; }
    public void setUsern(String usern) { this.usern = usern; }

}
