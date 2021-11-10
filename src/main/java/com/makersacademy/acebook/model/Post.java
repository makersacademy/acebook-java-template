package com.makersacademy.acebook.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private String photo;
    
    @OneToMany(mappedBy = "post") // the value of mappedBy is the name of the association-mapping attribute on the owning side
    private Set<Comment> comments = new HashSet<>();

    public Post() {}
    public Post(String content) { this.content = content; }
    public Post(String content, String timestamp) { this.content = content; this.timestamp = timestamp; }
    public Post(String content, String timestamp, String photo) { this.content = content; this.timestamp = timestamp; this.photo = photo; }

    public Long getId() {
        return id;
    }

    public String getContent() { return this.content; }
    public void setContent(String content) { this.content = content; }

    public String createTimestamp() {
        Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.SSS");  
        String timestamp = dateFormat.format(date);
        return timestamp;
    }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    
    public String getPhoto() { return this.photo; }
    public void setPhoto(String photo) { this.photo = photo; }

    // Below two blocks are needed for private Set<Comment> comments = new HashSet<>();
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null | getClass() != o.getClass()) return false;

        Post post = (Post) o;

        return id != null ? id.equals(post.id) : post.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
