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
  private Long id;
  private Long userid;
  private Long postid;

  public Like() {}

  public Like(Long userid, Long postid) {
    this.userid = userid;
    this.postid = postid;
  }

  public Long getId() { return this.id; }
  public Long getUserid() { return this.userid; }
  public void setUserid(Long userid) { this.userid = userid; }
  public Long getPostid() { return this.postid; }
  public void setPostid(Long postid) { this.postid = postid; }

}
