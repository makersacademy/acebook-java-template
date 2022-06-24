package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Test;

public class PostTest {

  private Post post = new Post("hello");

  @Test
  public void postHasMessage() {
    assertThat(post.getMessage(), containsString("hello"));
  }

}
