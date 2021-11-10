package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import javax.persistence.ManyToMany;
import javax.persistence.GenerationType;
import java.util.Set;

@Data
@Table(name = "LIKED_POSTS")
@Entity
public class Like {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private long post_id;
  private long user_id;
  // private String username;

  public Like(long userid, long postid) {
    this.user_id = userid;
    this.post_id = postid;
  }

  public void likepost() {

  }

}