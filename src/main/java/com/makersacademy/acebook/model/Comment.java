package com.makersacademy.acebook.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GenerationType;

import lombok.Data;

@Data
@Entity
@Table(name = "COMMENTS")
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "content")
  private String content;

  @Column(name = "timestamp")
  private Timestamp timestamp;

  @Column(name = "postid")
  private Long postid;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userid")
  private User user;

  public Comment() {
  }

  public Comment(String content, Long postid) {
    this.content = content;
    this.postid = postid;
  }

  public Long getId() { return this.id; }
  public String getContent() { return this.content; }
  public void setContent(String content) { this.content = content; }
  public Timestamp getTimestamp() { return this.timestamp; }
  public Long getPostid() { return this.postid; }
  public void setPostid(Long postid) { this.postid = postid; }
  public User getUser() { return user; }
  public void setUser(User user) { this.user = user; }

  public void generateTimestamp() {
    long now = System.currentTimeMillis();
    Timestamp timestamp = new Timestamp(now);
    this.timestamp = timestamp;
  }

}
