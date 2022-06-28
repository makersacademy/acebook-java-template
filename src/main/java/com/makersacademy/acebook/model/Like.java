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
@Table(name = "LIKES")
public class Like {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDateTime time;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "post_id", nullable = false)
  private Post post;

  public Like() {
    this.time = LocalDateTime.now();
  }

  public Like(User user, Post post) {
    this.time = LocalDateTime.now();
    this.user = user;
    this.post = post;
  }

  public Long getId() {
    return id;
  }

  public LocalDateTime getTime() {
    return time;
  }

  public User getUser() {
    return user;
  }

  public Post getPost() {
    return post;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void setPost(Post post) {
    this.post = post;
  }

}
