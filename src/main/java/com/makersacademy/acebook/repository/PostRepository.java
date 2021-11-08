package com.makersacademy.acebook.repository;
import com.makersacademy.acebook.model.Post;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

  Iterable<Post> findAll(Sort by);

}
