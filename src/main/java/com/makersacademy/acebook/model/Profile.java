package com.makersacademy.acebook.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;

import lombok.Data;

@Data
@Entity
@Table(name = "PROFILES")

public class Profile {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private long userId;
  private String bio;
  private String pronouns;
  private Date birthday;
  private String nickname;
  private String cover_image;

  public Profile() {}

  public Profile(long id, long userId, String bio, String pronouns, Date birthday, String nickname, String cover_image) {
    this.id = id;
    this.userId = userId;
    this.bio = bio;
    this.pronouns = pronouns;
    this.birthday = birthday;
    this.nickname = nickname;
    this.cover_image = cover_image;
  }

  public long getUserId() { return this.userId; }
  public void setUserId(long userId) { this.userId = userId; }

  public String getBio() { return this.bio; }
  public void setBio(String bio) { this.bio = bio; }

  public String getCoverImage() { return this.cover_image; }
  public void setCoverImage(String cover_image) { this.cover_image = cover_image; }

  public String getPronouns() { return this.pronouns; }
  public void setPronouns(String pronouns) { this.pronouns = pronouns; }

  public Date getBirthday() { return this.birthday; }
  public void setBirthday(Date birthday) { this.birthday = birthday; }

  public String getNickname() { return this.nickname; }
  public void setNickname(String nickname) { this.nickname = nickname; }
}
