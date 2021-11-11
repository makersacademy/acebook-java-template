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

}