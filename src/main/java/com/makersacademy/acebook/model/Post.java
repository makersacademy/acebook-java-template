package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;

import lombok.Data;
import java.time.LocalDate;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @CreationTimestamp
    private LocalDate postdate;


    private Post() {}

    public Post(String content) {
        this.postdate = postdate;
        this.content = content;
    }

}
