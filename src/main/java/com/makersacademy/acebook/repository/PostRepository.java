package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Post;

import antlr.collections.List;

import java.util.Set;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
  Set<Post> findByid(Long id);
}
