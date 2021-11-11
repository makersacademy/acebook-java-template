package com.makersacademy.acebook.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="comments") // to link to psql table
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // use IDENTITY to avoid hibernate sequence error and to be the same as other files
  private Long id;

  private String content;
  private Timestamp created_at; // Timestamp from java import. "create_at" is same as sql column name
  private Long user_id;
  private Long post_id;

  @ManyToOne // Many comments to one post
  @JoinColumn(name="post_id", insertable=false, updatable=false) // "post_id" is same as sql column name
  private Post post;

  @ManyToOne
  @JoinColumn(name="user_id", insertable=false, updatable=false)
  private User user;

  // Constructors
  public Comment() {
  }
  public Comment(Long id, String content) {
    this.id = id;
    this.content = content;
  }

  // Getter & setter for id, user_id, post_id
  public Long getId() {
    return id;
  }
  // public void setId(Long id) {
  //   this.id = id;
  // }
  public Long getUserId() {
    return user_id;
  }
  public void setUserId(Long user_id) {
    this.user_id = user_id;
  }
  public Long getPostId() {
    return post_id;
  }
  public void setPostId(Long post_id) {
    this.post_id = post_id;
  }

  // Getter & setter for content
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  
  // Getter & setter for timestamp
  public Timestamp getCreatedAt() {
    return created_at;
  }
  public void setCreatedAt(Timestamp created_at) {
    this.created_at = created_at;
  }

  // Getter & setter for User
  public User getUser() {
    return this.user;
  }
  public void setUser(User user) {
    this.user = user;
  }
}
