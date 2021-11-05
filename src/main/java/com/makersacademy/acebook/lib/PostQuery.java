package com.makersacademy.acebook.lib;

import java.util.Date;

public class PostQuery {
  public String content;
  public String usern;
  public Date time;
  

  public PostQuery(String content, String usern, Date time){
    this.content = content;
    this.time = time;
    this.usern = usern;
  }
}
