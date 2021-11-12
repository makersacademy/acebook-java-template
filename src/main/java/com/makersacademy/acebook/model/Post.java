package com.makersacademy.acebook.model;

import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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
private String username;

@OneToMany(mappedBy = "post") // the value of mappedBy is the name of the association-mapping attribute on the owning side
 private List<Comment> comments = new ArrayList<>();

public Post() {
}

public Post(String content) {
  this.content = content;
}

public Post(String content, String timestamp) {
  this.content = content;
  this.timestamp = timestamp;
}

public Post(String content, String timestamp, String photo) {
  this.content = content;
  this.timestamp = timestamp;
  this.photo = photo;
}

public Post(String content, String timestamp, String photo, String username) {
  this.content = content;
  this.timestamp = timestamp;
  this.photo = photo;
  this.username = username;
}

public Long getId() {
      return id;
  }


public String getContent() {
  return this.content;
}

public void setContent(String content) {
  this.content = content;
}

public String createTimestamp() {
  Date date = Calendar.getInstance().getTime();
  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
  String timestamp = dateFormat.format(date);
  return timestamp;
}

public void setTimestamp(String timestamp) {
  this.timestamp = timestamp;
}

public String getTimestamp() {
  return this.timestamp;
}

public String getPhoto() {
  return this.photo;
}

public void setPhoto(String photo) {
  this.photo = photo;
}

public String getUsername() {
  return username;
}

public void setUsername(String username) {
  this.username = username;
}

 // Getter and Setter for comments
  public List<Comment> getComments() {
      return this.comments;
  }
  public void setComments(List<Comment> comments) {
      this.comments = comments;
  }
}
