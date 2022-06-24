
package com.makersacademy.acebook.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GenerationType;

import static java.lang.Boolean.TRUE;

import lombok.Data;

@Data
@Entity
@Table(name = "USERS")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String email;
  private String password;
  private String name;
  private String about;
  private String imageUrl;
  private LocalDateTime time;
  private Boolean enabled;

  public User() {
    this.time = LocalDateTime.now();
    this.enabled = TRUE;
  }

  public User(String email, String password) {
    this.email = email;
    this.password = password;
    this.time = LocalDateTime.now();
    this.enabled = TRUE;
  }

  public User(String email, String name, String password) {
    this.email = email;
    this.password = password;
    this.time = LocalDateTime.now();
    this.enabled = TRUE;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAbout() {
    return about;
  }

  public void setAbout(String about) {
    this.about = about;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public LocalDateTime getTime() {
    return time;
  }

  public void setTime(LocalDateTime time) {
    this.time = time;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public Long getId() {
    return id;
  }

}
