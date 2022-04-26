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
@Table(name = "COMMENTS")
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String content;
  private Timestamp timestamp;
  private Long userid;
  private Long postid;

  public Comment() {
  }

  public Comment(String content, Long userid, Long postid) {
    this.content = content;
    this.userid = userid;
    this.postid = postid;
  }

  public Long getId() { return this.id; }
  public String getContent() { return this.content; }
  public void setContent(String content) { this.content = content; }
  public Timestamp getTimestamp() { return this.timestamp; }
  public Long getUserid() { return this.userid; }
  public void setUserid(Long userid) { this.userid = userid; }
  public Long getPostid() { return this.postid; }
  public void setPostid(Long postid) { this.postid = postid; }

  public void generateTimestamp() {
    long now = System.currentTimeMillis();
    Timestamp timestamp = new Timestamp(now);
    this.timestamp = timestamp;
  }

}
