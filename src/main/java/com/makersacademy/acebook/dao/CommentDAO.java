package com.makersacademy.acebook.dao;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface CommentDAO extends CrudRepository<Comment, Long> {
    Iterable<Comment> findByPost(Post post);
}
