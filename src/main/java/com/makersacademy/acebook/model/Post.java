package com.makersacademy.acebook.model;

import javax.persistence.*;

import lombok.Data;
import net.bytebuddy.asm.Advice.Local;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy;

import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.sql.Date;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @CreatedDate
    private Date createdDate;

    public Post() {
        // this constructor is used when post models are created based on data from the posts table
        // but somehow the dates are not all set to "now"
        this.createdDate = java.sql.Date.valueOf(LocalDate.now());
    }

    public String getContent()             { return this.content; }
    public Date getCreatedDate()        { return this.createdDate; }
    public void setContent(String content) { this.content = content; }
}
