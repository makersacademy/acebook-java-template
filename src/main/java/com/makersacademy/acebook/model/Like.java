package com.makersacademy.acebook.model;

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
@Table(name = "LIKES")
public class Like {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private Long id;

  @Column(name = "postid")
  private Long postid;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userid")
  private User user;

  public Like() {}

  public User getUser() { return user; }
  public void setUser(User user) { this.user = user; }
  public Long getId() { return this.id; }
  public Long getPostid() { return this.postid; }
  public void setPostid(Long postid) { this.postid = postid; }

}
