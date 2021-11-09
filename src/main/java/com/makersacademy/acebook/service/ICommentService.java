package com.makersacademy.acebook.service;

import java.util.List;
import java.util.Optional;

import com.makersacademy.acebook.model.Comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICommentService {
    Optional<Comment> findById(Long postId);

    Page<Comment> findByPostId(Long postId, Pageable pageable);

    Comment save(Comment comment);

}
