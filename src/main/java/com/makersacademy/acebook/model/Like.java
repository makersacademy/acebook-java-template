package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;

import lombok.Data;

@Data
@Entity
@Table(name = "LIKES")
public class Like {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;
  private Long postId;

  public Like() {}

  public Like(Long userId, Long postId) {
    this.userId = userId;
    this.postId = postId;
  }

  public Long getUserId() { return this.userId; }
  public void setUserId(Long userId) { this.userId = userId; }
  public Long getPostId() { return this.postId; }
  public void setPostId(Long postId) { this.postId = postId; }

}
