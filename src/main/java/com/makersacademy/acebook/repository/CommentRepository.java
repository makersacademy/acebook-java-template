package com.makersacademy.acebook.repository;

import org.springframework.data.repository.CrudRepository;
import com.makersacademy.acebook.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}