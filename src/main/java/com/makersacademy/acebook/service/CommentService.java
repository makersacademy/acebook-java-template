package com.makersacademy.acebook.service;

import java.util.List;
import java.util.Optional;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private CommentRepository repository;

    @Override
    public Optional<Comment> findById(Long postId) {
        return repository.findById(postId);
    }

    @Override
    public Page<Comment> findByPostId(Long postId, Pageable pageable) {
        return repository.findByPostId(postId, pageable);
    }

    @Override
    public Comment save(Comment comment) {
        return repository.save(comment);
    }

}
