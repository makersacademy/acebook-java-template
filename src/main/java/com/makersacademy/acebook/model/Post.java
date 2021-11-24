package com.makersacademy.acebook.model;

import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String tests;
//    private Date stamp;

    public Post() {}

    public Post(String content) {
        this.content = content;
    }

//    public Date getStamp() {
//       return this.stamp;
//    }
//    public String setStamp() {
//        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
//        return timeStamp;
//    }
    public String getTests() { return this.tests; }
    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }

}
