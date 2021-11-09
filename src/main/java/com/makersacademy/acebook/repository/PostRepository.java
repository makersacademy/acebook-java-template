package com.makersacademy.acebook.repository;

import java.util.List;

import com.makersacademy.acebook.model.Post;

// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {

  // @Query("SELECT new com.makersacademy.acebook.lib.PostQuery(p.content,
  // p.usern, p.time, p.id) FROM Post p ORDER BY p.time DESC")
  // List<PostQuery> postsSortedByDate();

}
