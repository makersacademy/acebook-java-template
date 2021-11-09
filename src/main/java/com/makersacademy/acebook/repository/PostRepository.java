package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Post;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {

}
