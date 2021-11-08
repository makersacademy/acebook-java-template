package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    Page<Comment> findByPostId(Long postId, Pageable pageable);
}