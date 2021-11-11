package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import lombok.Data;

import javax.persistence.ManyToMany;
import javax.persistence.GenerationType;
import java.util.Set;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Data
@Table(name = "LIKES")
@Entity
public class Like {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private long post_id;
  private long user_id;

  public Like(long user_id, long post_id) {
    this.user_id = user_id;
    this.post_id = post_id;
  }

  public void like() {

  }

  // @Formula("SELECT COUNT(post_id) FROM likes l WHERE l.post_id = post_id")
  // public int likeCount;

  // public int likeCount() {
  // return likeCount;
  // }

}