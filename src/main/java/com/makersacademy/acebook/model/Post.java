package com.makersacademy.acebook.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.sql.Date;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Integer likes;

    @CreationTimestamp
    private Timestamp stamp;


    public Post() {}

    public Post(String content) {
        this.content = content;
    }

    public Timestamp getStamp() {return this.stamp;}

    public Integer getLikes(){return this.likes;}
    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }

}
