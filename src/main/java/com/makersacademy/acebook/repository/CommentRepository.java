package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Comment;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {

}
