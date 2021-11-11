package com.makersacademy.acebook.repository;

import java.util.List;

import com.makersacademy.acebook.model.Comment;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {
    List<Comment> findByPostId(Long id);
    List<Comment> findAllByPostId(Long post_id);

}
