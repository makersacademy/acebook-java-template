package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    public List<Comment> findCommentByPost_id(Long post_id);
}
