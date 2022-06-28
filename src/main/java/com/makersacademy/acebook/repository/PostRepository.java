package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Post;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
  public List<Post> findAllByOrderByTimeDesc();

  public List<Post> findAllById(Long Id);
}
