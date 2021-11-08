package com.makersacademy.acebook.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
    // public String user_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;
    // public String getUsername() { return username; }

    public Post() {
    }

    public Post(String content, String usern, Timestamp time, Long id) {
        this.content = content;
        this.usern = usern;
        this.time = time;
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsern() {
        return this.usern;
    }

    public void setUsern(String usern) {
        this.usern = usern;
    }

}
