package com.makersacademy.acebook.model;

import lombok.Data;
import org.hibernate.type.descriptor.sql.NumericTypeDescriptor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Date stamp;
    private Integer likes;

    public Post() {}

    public Post(String content) {
        this.content = content;
    }

    public Date getStamp() {return this.stamp;}
    public Integer getLikes(){return this.likes;}
    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }

}
