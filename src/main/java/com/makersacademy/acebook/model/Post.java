package com.makersacademy.acebook.model;
import java.sql.Timestamp;
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
    private String content;
    private Timestamp timestamp;
    private Long likes;
    private Long userid;
    private String imagepath;

    public Post() {}

    public Post(String content, String imagepath) {
        this.content = content;
        this.imagepath = imagepath;
    }

    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }
    public Timestamp getTimestamp() { return this.timestamp; }
    public Long getId() { return this.id; }
    public Long getLikes() { return this.likes; }
    public void setLikes(Long likes) { this.likes = likes;}
    public Long getUserId() { return this.userid; }

    public String getImagepath() { return this.imagepath; }
    public void setImagepath(String imagepath) { this.imagepath = imagepath; }

    
    public void generateTimestamp() {
        long now = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(now);
        this.timestamp = timestamp;
    }

    public void addUserID(Long id) {
        this.userid = id;
    }

}
