package com.makersacademy.acebook.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
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
    public String content;
    private Long user_id;
    private int likes;
    private Date time_created;

    @Column(name="time_created")			//database column name? (time_created?)
	

    public void addLikes() {
        likes++;
    }

    public Post() {}

    public Post(String content, int likes) {
        this.content = content;
        this.likes = 0;
    }
    public String getContent() { return this.content; }
    public int getLikes() {return this.likes;}
    public void setContent(String content) { this.content = content; }
    public void setLikes(int likes) { this.likes = 0; }
    public void setUserId(Long userId){ this.user_id = userId; }
    public void setDate(Date timeStamp){ this.time_created = timeStamp; }
    public Long getId(){ return this.id; }

}
