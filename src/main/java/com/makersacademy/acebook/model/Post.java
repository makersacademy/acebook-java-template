package com.makersacademy.acebook.model;

import javax.persistence.*;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @CreatedDate
    private Instant createdDate;

    public Post() {
        // this constructor is used when post models are created based on data from the posts table
        // but somehow the dates are not all set to "now"
        this.createdDate = Instant.now();
    }

    public String getContent()             { return this.content; }
    public Instant getCreatedDate()        { return this.createdDate; }
    public void setContent(String content) { this.content = content; }
}
