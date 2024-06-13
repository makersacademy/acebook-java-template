package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import org.springframework.data.repository.CrudRepository;

public interface LikeRepository extends CrudRepository<Like, Long> {
    Like findByUserAndPost(User user, Post post);
    long countByPost(Post post);
}