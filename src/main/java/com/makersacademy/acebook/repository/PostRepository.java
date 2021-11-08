package com.makersacademy.acebook.repository;

import java.util.List;

import com.makersacademy.acebook.lib.PostQuery;
import com.makersacademy.acebook.model.Post;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
  
  @Query("SELECT new com.makersacademy.acebook.lib.PostQuery(p.content, p.usern, p.time, p.id) FROM Post p ORDER BY p.time DESC")
  List<PostQuery> postsSortedByDate();

}
