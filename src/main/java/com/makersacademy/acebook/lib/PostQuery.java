package com.makersacademy.acebook.lib;

import java.util.Date;

public class PostQuery {
  public String content;
  public Date time;
  public Long id;

  public PostQuery(String content, Date time, Long id) {
    this.content = content;
    this.time = time;
    this.id = id;
  }
}
