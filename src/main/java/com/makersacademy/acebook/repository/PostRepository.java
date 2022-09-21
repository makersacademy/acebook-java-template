package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

 public default Post filterPostsById() {
  PostRepository repository;
  List<Post> posts = (List<Post>) repository.findAll();
  Object[] ordered_posts = {};
 //  for (int i = posts.length; i > 0; i--) {
 //   ordered_posts.add(posts.get(i));
 //  }
 //  return posts;
 // }
}
