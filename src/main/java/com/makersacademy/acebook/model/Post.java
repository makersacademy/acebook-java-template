package com.makersacademy.acebook.model;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import lombok.Data;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="content")
    private String content;

    @Column(name="timestamp")
    private Timestamp timestamp;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userid")
    private User user;

    public Post() {}

    public Post(String content) {
        this.content = content;
    }
    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }
    public Timestamp getTimestamp() { return this.timestamp; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user;  }
    public Long getId() {return this.id; }
    
    public void generateTimestamp() {
        long now = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(now);
        this.timestamp = timestamp;
    }
}
