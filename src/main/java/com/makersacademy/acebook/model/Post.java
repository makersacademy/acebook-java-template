
package com.makersacademy.acebook.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
  private String message;
  private String imageUrl;
  private Long likesCount;
  private LocalDateTime time;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  public Post() {
    this.time = LocalDateTime.now();
  }

  public Post(String message) {
    this.message = message;
    this.time = LocalDateTime.now();
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void getUser(User user) {
    this.user = user;
  }

  public Long getLikesCount() {
    return this.likesCount;
  }

  public void setLikesCount(Long likesCount) {
    this.likesCount = likesCount;
  }
}
