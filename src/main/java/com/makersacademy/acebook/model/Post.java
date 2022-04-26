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
    private String imagepath;
    private Integer userid;

    public Post() {}

    public Post(String content, String imagepath) {
        this.content = content;
        this.imagepath = imagepath;
    }

    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }
    public Timestamp getTimestamp() { return this.timestamp; }

    public String getImagepath() { return this.imagepath; }
    public void setImagepath(String imagepath) { this.imagepath = imagepath; }

    public Integer getUserId() { return this.userid; }

    
    public void generateTimestamp() {
        long now = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(now);
        this.timestamp = timestamp;
    }
  
    public void addUserID(Integer id) {
        this.userid = id;
    }

}
