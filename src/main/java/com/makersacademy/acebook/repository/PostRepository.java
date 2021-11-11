package com.makersacademy.acebook.repository;

import java.util.List;

import com.makersacademy.acebook.model.Post;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {

  List<Post> findByUserId(Long user, Sort by);

}
