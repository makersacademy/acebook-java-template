package com.makersacademy.acebook.model;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    private String timestamp;

    public Post() {}
    public Post(String content) { this.content = content; }
    public Post(String content, String timestamp) { this.content = content; this.timestamp = timestamp; }
    
    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }
    public String createTimestamp() { 
        
        Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.SSS");  
        String timestamp = dateFormat.format(date);
        return timestamp;

     }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

}
