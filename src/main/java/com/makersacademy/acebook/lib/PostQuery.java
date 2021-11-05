package com.makersacademy.acebook.lib;

import java.util.Date;

public class PostQuery {
  public String content;
  public Date time;

  public PostQuery(String content, Date time){
    this.content = content;
    this.time = time;
  }
}
